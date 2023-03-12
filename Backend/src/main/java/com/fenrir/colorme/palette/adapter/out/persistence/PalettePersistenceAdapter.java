package com.fenrir.colorme.palette.adapter.out.persistence;

import com.fenrir.colorme.common.annotation.PersistenceAdapter;
import com.fenrir.colorme.palette.application.port.out.CreatePalettePort;
import com.fenrir.colorme.palette.domain.Palette;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
class PalettePersistenceAdapter implements CreatePalettePort {
    private final PaletteRepository paletteRepository;
    private final PaletteColorRepository paletteColorRepository;
    private final PaletteTagRepository paletteTagRepository;
    private final EntityManager entityManager;
    private final PaletteEntityMapper paletteMapper;

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
}
