package com.fenrir.colorme.palette.application.service;

import com.fenrir.colorme.common.annotation.UseCase;
import com.fenrir.colorme.palette.application.mapper.PaletteMapper;
import com.fenrir.colorme.palette.application.port.in.createpalette.CreatePaletteCommand;
import com.fenrir.colorme.palette.application.port.in.createpalette.CreatePaletteResponse;
import com.fenrir.colorme.palette.application.port.in.createpalette.CreatePaletteUseCase;
import com.fenrir.colorme.palette.application.port.out.CreatePalettePort;
import com.fenrir.colorme.palette.domain.Palette;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
class CreatePaletteService implements CreatePaletteUseCase {
    private final CreatePalettePort createPalettePort;
    private final PaletteMapper paletteMapper;

    @Override
    public CreatePaletteResponse createPalette(CreatePaletteCommand command) {
        final Palette palette = paletteMapper.toPalette(command);
        createPalettePort.createPalette(palette);
        return paletteMapper.toCreatePaletteResponse(palette);
    }
}
