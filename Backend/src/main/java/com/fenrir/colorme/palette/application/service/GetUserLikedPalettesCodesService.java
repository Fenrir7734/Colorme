package com.fenrir.colorme.palette.application.service;

import com.fenrir.colorme.common.annotation.UseCase;
import com.fenrir.colorme.common.security.AuthenticationFacade;
import com.fenrir.colorme.palette.application.port.in.GetUserLikedPalettesCodesUseCase;
import com.fenrir.colorme.palette.application.port.out.GetUserLikedPalettesCodesPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional
class GetUserLikedPalettesCodesService implements GetUserLikedPalettesCodesUseCase {
    private final GetUserLikedPalettesCodesPort getUserLikedPalettesCodesPort;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public List<String> getUserLikedPalettesCodes() {
        Long userId = authenticationFacade.getUserId();
        return getUserLikedPalettesCodesPort.getLikedPalettesCodes(userId)
                .stream()
                .map(String::trim)
                .toList();
    }
}
