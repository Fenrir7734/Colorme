package com.fenrir.colorme.palette.application.service;

import com.fenrir.colorme.common.security.AuthenticationFacade;
import com.fenrir.colorme.palette.application.port.out.DeletePaletteLikePort;
import com.fenrir.colorme.palette.application.port.out.PaletteLikeExistsPort;
import com.fenrir.colorme.palette.application.service.exception.PaletteLikeNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

class DeletePaletteLikeServiceTest {
    private final PaletteLikeExistsPort likeExistsPort = Mockito.mock(PaletteLikeExistsPort.class);
    private final DeletePaletteLikePort deleteLikePort = Mockito.mock(DeletePaletteLikePort.class);
    private final AuthenticationFacade authenticationFacade = Mockito.mock(AuthenticationFacade.class);

    private final DeletePaletteLikeService deletePaletteService = new DeletePaletteLikeService(likeExistsPort, deleteLikePort, authenticationFacade);

    @Test
    void deletesLike() {
        // given
        final String code = "1234";
        final Long userId = 1L;

        givenUserLikeExists(code, userId);

        // when
        deletePaletteService.deleteLike(code);

        // then
        then(deleteLikePort).should().deleteLike(code, userId);
    }

    @Test
    void failsToDeleteNotExistingLike() {
        // given
        final String code = "1234";
        final Long userId = 1L;

        givenUserLikeNotExists(code, userId);

        // when & then
        assertThatThrownBy(() -> deletePaletteService.deleteLike(code))
                .isInstanceOf(PaletteLikeNotFoundException.class);

        then(deleteLikePort).should(never()).deleteLike(code, userId);
    }

    private void givenUserLikeExists(String code, Long userId) {
        given(authenticationFacade.getUserId()).willReturn(userId);
        given(likeExistsPort.likeExits(code, userId)).willReturn(true);
    }

    private void givenUserLikeNotExists(String code, Long userId) {
        given(authenticationFacade.getUserId()).willReturn(userId);
        given(likeExistsPort.likeExits(code, userId)).willReturn(false);
    }
}