package com.fenrir.colorme.palette.adapter.out.persistence;

import com.fenrir.colorme.common.annotation.PersistenceAdapter;
import com.fenrir.colorme.palette.application.port.out.CreatePalettePort;
import com.fenrir.colorme.palette.application.port.out.DeletePalettePort;
import com.fenrir.colorme.palette.application.port.out.GetPalettePort;
import com.fenrir.colorme.palette.application.port.out.PaletteExistsPort;
import com.fenrir.colorme.palette.domain.Palette;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
class PalettePersistenceAdapter implements GetPalettePort, PaletteExistsPort, CreatePalettePort, DeletePalettePort {
    private final PaletteRepository paletteRepository;
    private final PaletteColorRepository paletteColorRepository;
    private final PaletteTagRepository paletteTagRepository;
    private final PaletteLikeRepository paletteLikeRepository;
    private final EntityManager entityManager;
    private final PaletteEntityMapper paletteMapper;

    @Override
    public Optional<Palette> getPalette(String code) {
        return paletteRepository.findByCode(code).map(paletteMapper::toPalette);
    }

    @Override
    public boolean paletteExists(String code) {
        return paletteRepository.existsByCode(code);
    }

    @Override
    public void createPalette(Palette palette) {
        PaletteEntity paletteEntity = paletteMapper.toPaletteEntity(palette);
        paletteRepository.save(paletteEntity);
        entityManager.refresh(paletteEntity);

        persisPaletteColors(paletteEntity);
        persistPaletteTags(paletteEntity);

        palette.setId(paletteEntity.getId());
        palette.setCode(paletteEntity.getCode());
    }

    private void persisPaletteColors(PaletteEntity palette) {
        List<PaletteColorEntity> colors = palette.getColors()
                .stream()
                .peek(color -> color.setPalette(palette))
                .toList();
        paletteColorRepository.saveAll(colors);
    }

    private void persistPaletteTags(PaletteEntity palette) {
        List<PaletteTagEntity> tags = palette.getTags()
                .stream()
                .peek(tag -> tag.getId().setPaletteId(palette.getId()))
                .toList();
        paletteTagRepository.saveAll(tags);
    }

    @Override
    public void deletePalette(String code) {
        paletteColorRepository.deleteAllByPaletteCode(code);
        paletteTagRepository.deleteAllByPaletteCode(code);
        paletteLikeRepository.deleteAllByPaletteCode(code);
        paletteRepository.deleteByCode(code);
    }
}
