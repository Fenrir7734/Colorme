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
    private final PaletteEntityMapper paletteMapper;
    private final EntityManager entityManager;

    @Override
    public void createPalette(Palette palette) {
        PaletteEntity paletteEntity = paletteMapper.toPaletteEntity(palette);
        List<PaletteColorEntity> colors = paletteEntity.getColors()
                .stream()
                .peek(color -> color.setPalette(paletteEntity))
                .toList();

        paletteRepository.save(paletteEntity);
        paletteColorRepository.saveAll(colors);
        entityManager.refresh(paletteEntity);

        palette.setId(paletteEntity.getId());
        palette.setCode(paletteEntity.getCode());
    }
}
