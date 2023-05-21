package com.fenrir.colorme.palette.application.service;

import com.fenrir.colorme.common.annotation.UseCase;
import com.fenrir.colorme.common.security.AuthenticationFacade;
import com.fenrir.colorme.palette.application.port.in.CreatePaletteLikeUseCase;
import com.fenrir.colorme.palette.application.port.out.CreatePaletteLikePort;
import com.fenrir.colorme.palette.application.port.out.GetPalettePort;
import com.fenrir.colorme.palette.application.port.out.PaletteLikeExistsPort;
import com.fenrir.colorme.palette.application.service.exception.PaletteLikeAlreadyExistsException;
import com.fenrir.colorme.palette.application.service.exception.PaletteNotFoundException;
import com.fenrir.colorme.palette.domain.Palette;
import com.fenrir.colorme.palette.domain.PaletteLike;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
class CreatePaletteLikeService implements CreatePaletteLikeUseCase {
    private final GetPalettePort getPalettePort;
    private final PaletteLikeExistsPort likeExistsPort;
    private final CreatePaletteLikePort createLikePort;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public void createLike(String paletteCode) {
        final Long userId = authenticationFacade.getUserId();
        validate(paletteCode, userId);

        Palette palette = getPalette(paletteCode);
        PaletteLike like = new PaletteLike(palette.getId(), userId);
        createLikePort.createLike(like);
    }

    private void validate(String paletteCode, Long userId) {
        if (likeExistsPort.likeExits(paletteCode, userId)) {
            throw new PaletteLikeAlreadyExistsException(paletteCode);
        }
    }

    private Palette getPalette(String paletteCode) {
        return getPalettePort.getPalette(paletteCode)
                .orElseThrow(() -> new PaletteNotFoundException(paletteCode));
    }
}
