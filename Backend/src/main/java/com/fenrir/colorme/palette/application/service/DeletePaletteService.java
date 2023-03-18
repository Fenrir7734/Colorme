package com.fenrir.colorme.palette.application.service;

import com.fenrir.colorme.common.annotation.UseCase;
import com.fenrir.colorme.palette.application.port.in.DeletePaletteUseCase;
import com.fenrir.colorme.palette.application.port.out.DeletePalettePort;
import com.fenrir.colorme.palette.application.port.out.PaletteExistsPort;
import com.fenrir.colorme.palette.application.service.exception.PaletteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
class DeletePaletteService implements DeletePaletteUseCase {
    private final DeletePalettePort deletePalettePort;
    private final PaletteExistsPort paletteExistsPort;

    @Override
    public void deletePalette(String code) {
        if (paletteExistsPort.paletteExists(code)) {
            deletePalettePort.deletePalette(code);
        } else {
            throw new PaletteNotFoundException(code);
        }
    }
}
