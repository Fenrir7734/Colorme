package com.fenrir.colorme.palette.application.service;

import com.fenrir.colorme.common.annotation.UseCase;
import com.fenrir.colorme.common.security.AuthenticationFacade;
import com.fenrir.colorme.palette.application.port.in.DeletePaletteUseCase;
import com.fenrir.colorme.palette.application.port.out.DeletePalettePort;
import com.fenrir.colorme.palette.application.port.out.PaletteExistsPort;
import com.fenrir.colorme.palette.application.port.out.UserPaletteExistsPort;
import com.fenrir.colorme.palette.application.service.exception.NotPaletteOwnerException;
import com.fenrir.colorme.palette.application.service.exception.PaletteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
class DeletePaletteService implements DeletePaletteUseCase {
    private final DeletePalettePort deletePalettePort;
    private final PaletteExistsPort paletteExistsPort;
    private final UserPaletteExistsPort userPaletteExistsPort;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public void deletePalette(String code) {
        validate(code);
        deletePalettePort.deletePalette(code);
    }

    private void validate(String code) {
        if (!paletteExistsPort.paletteExists(code)) {
            throw new PaletteNotFoundException(code);
        }

        if (!isPaletteOwnerOrAdmin(code)) {
            throw new NotPaletteOwnerException(code);
        }
    }

    private boolean isPaletteOwnerOrAdmin(String code) {
        return authenticationFacade.isAdmin() ||
                this.userPaletteExistsPort.paletteExists(
                        code,
                        authenticationFacade.getUserId());
    }
}
