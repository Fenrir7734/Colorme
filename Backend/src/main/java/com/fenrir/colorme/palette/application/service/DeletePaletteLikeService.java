package com.fenrir.colorme.palette.application.service;

import com.fenrir.colorme.common.annotation.UseCase;
import com.fenrir.colorme.common.security.AuthenticationFacade;
import com.fenrir.colorme.palette.application.port.in.DeletePaletteLikeUseCase;
import com.fenrir.colorme.palette.application.port.out.DeletePaletteLikePort;
import com.fenrir.colorme.palette.application.port.out.PaletteLikeExistsPort;
import com.fenrir.colorme.palette.application.service.exception.PaletteLikeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
class DeletePaletteLikeService implements DeletePaletteLikeUseCase {
    private final PaletteLikeExistsPort likeExistsPort;
    private final DeletePaletteLikePort deleteLikePort;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public void deleteLike(String paletteCode) {
        final Long userId = authenticationFacade.getUserId();
        validate(paletteCode, userId);
        deleteLikePort.deleteLike(paletteCode, userId);
    }

    private void validate(String paletteCode, Long userId) {
        if (!likeExistsPort.likeExits(paletteCode, userId)) {
            throw new PaletteLikeNotFoundException(paletteCode);
        }
    }
}
