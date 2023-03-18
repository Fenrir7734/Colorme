package com.fenrir.colorme.palette.application.service;

import com.fenrir.colorme.common.annotation.UseCase;
import com.fenrir.colorme.palette.application.port.in.getpalette.GetPaletteResponse;
import com.fenrir.colorme.palette.application.port.in.getpalette.GetPaletteUseCase;
import com.fenrir.colorme.palette.application.port.out.GetPalettePort;
import com.fenrir.colorme.palette.application.service.exception.PaletteNotFoundException;
import com.fenrir.colorme.palette.domain.Palette;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
class GetPaletteService implements GetPaletteUseCase {
    private final GetPalettePort getPalettePort;
    private final PaletteMapper paletteMapper;

    @Override
    public GetPaletteResponse getPalette(String code) {
        Palette palette = getPalettePort.getPalette(code)
                .orElseThrow(() -> new PaletteNotFoundException(code));
        return paletteMapper.toGetPaletteResponse(palette);
    }
}
