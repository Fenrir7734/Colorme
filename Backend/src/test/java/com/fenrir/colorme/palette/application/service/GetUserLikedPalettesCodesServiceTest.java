package com.fenrir.colorme.palette.application.service;

import com.fenrir.colorme.common.security.AuthenticationFacade;
import com.fenrir.colorme.palette.application.port.out.GetUserLikedPalettesCodesPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class GetUserLikedPalettesCodesServiceTest {
    private final GetUserLikedPalettesCodesPort getUserLikedPalettesCodesPort = Mockito.mock(GetUserLikedPalettesCodesPort.class);
    private final AuthenticationFacade authenticationFacade = Mockito.mock(AuthenticationFacade.class);

    private final GetUserLikedPalettesCodesService getUserLikedPalettesCodesService = new GetUserLikedPalettesCodesService(getUserLikedPalettesCodesPort, authenticationFacade);

    @Test
    void getsUserLikedPalettesCodes() {
        // given
        final Long userId = 1L;

        givenGetUserIdWillSuccess(userId);

        // when
        getUserLikedPalettesCodesService.getUserLikedPalettesCodes();

        // then
        then(getUserLikedPalettesCodesPort).should().getLikedPalettesCodes(userId);
    }

    private void givenGetUserIdWillSuccess(Long userId) {
        given(authenticationFacade.getUserId()).willReturn(userId);
    }
}