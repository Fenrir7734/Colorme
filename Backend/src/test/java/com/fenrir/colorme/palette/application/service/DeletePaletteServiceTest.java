package com.fenrir.colorme.palette.application.service;

import com.fenrir.colorme.common.security.AuthenticationFacade;
import com.fenrir.colorme.palette.application.port.out.DeletePalettePort;
import com.fenrir.colorme.palette.application.port.out.PaletteExistsPort;
import com.fenrir.colorme.palette.application.port.out.UserPaletteExistsPort;
import com.fenrir.colorme.palette.application.service.exception.NotPaletteOwnerException;
import com.fenrir.colorme.palette.application.service.exception.PaletteNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

class DeletePaletteServiceTest {
    private final DeletePalettePort deletePalettePort = Mockito.mock(DeletePalettePort.class);
    private final PaletteExistsPort paletteExistsPort = Mockito.mock(PaletteExistsPort.class);
    private final UserPaletteExistsPort userPaletteExistsPort = Mockito.mock(UserPaletteExistsPort.class);
    private final AuthenticationFacade authenticationFacade = Mockito.mock(AuthenticationFacade.class);

    private final DeletePaletteService deletePaletteService = new DeletePaletteService(deletePalettePort, paletteExistsPort, userPaletteExistsPort, authenticationFacade);

    @Test
    void deletesPaletteByOwner() {
        // given
        final String code = "1234";
        final Long ownerId = 1L;

        givenPaletteExists(code);
        givenUserIsOwnerAndNotAdmin(code, ownerId);

        // when
        deletePaletteService.deletePalette(code);

        // then
        then(deletePalettePort).should().deletePalette(code);
    }

    @Test
    void deletesPaletteByAdmin() {
        // given
        final String code = "1234";

        givenPaletteExists(code);
        givenUserIsAdmin();

        // when
        deletePaletteService.deletePalette(code);

        // then
        then(deletePalettePort).should().deletePalette(code);
    }

    @Test
    void failsToDeletePaletteDueToInvalidCode() {
        // given
        final String code = "1234";

        givenPaletteNotExists(code);

        // when & then
        assertThatThrownBy(() -> deletePaletteService.deletePalette(code))
                .isInstanceOf(PaletteNotFoundException.class);

        then(deletePalettePort).should(never()).deletePalette(any());
    }

    @Test
    void failsToDeletePaletteIfUserIsNotOwnerAndAdmin() {
        // given
        final String code = "1234";
        final Long ownerId = 1L;

        givenPaletteExists(code);
        givenUserIsNotOwnerAndNotAdmin(code, ownerId);

        // when & then
        assertThatThrownBy(() -> deletePaletteService.deletePalette(code))
                .isInstanceOf(NotPaletteOwnerException.class);

        then(deletePalettePort).should(never()).deletePalette(any());
    }

    private void givenPaletteExists(String code) {
        given(paletteExistsPort.paletteExists(code)).willReturn(true);
    }

    private void givenPaletteNotExists(String code) {
        given(paletteExistsPort.paletteExists(code)).willReturn(false);
    }

    private void givenUserIsOwnerAndNotAdmin(String code, Long userId) {
        given(authenticationFacade.isAdmin()).willReturn(false);
        given(authenticationFacade.getUserId()).willReturn(userId);
        given(userPaletteExistsPort.paletteExists(code, userId)).willReturn(true);
    }

    private void givenUserIsNotOwnerAndNotAdmin(String code, Long userId) {
        given(authenticationFacade.isAdmin()).willReturn(false);
        given(authenticationFacade.getUserId()).willReturn(userId);
        given(userPaletteExistsPort.paletteExists(code, userId)).willReturn(false);
    }

    private void givenUserIsAdmin() {
        given(authenticationFacade.isAdmin()).willReturn(true);
    }
}