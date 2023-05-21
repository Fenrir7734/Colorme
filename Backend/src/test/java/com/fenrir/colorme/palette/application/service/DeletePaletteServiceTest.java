package com.fenrir.colorme.palette.application.service;

import com.fenrir.colorme.common.security.AuthenticationFacade;
import com.fenrir.colorme.palette.application.port.out.DeletePalettePort;
import com.fenrir.colorme.palette.application.port.out.PaletteExistsPort;
import com.fenrir.colorme.palette.application.port.out.UserPaletteExistsPort;
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
    public void deletesPalette() {
        // given
        final String code = "1234";

        givenPaletteExists(code);

        // when
        deletePaletteService.deletePalette(code);

        // then
        then(deletePalettePort).should().deletePalette(code);
    }

    @Test
    public void failsToDeletePaletteDueToInvalidCode() {
        // given
        final String code = "1234";

        givenPaletteNotExists(code);

        // when & then
        assertThatThrownBy(() -> deletePaletteService.deletePalette(code))
                .isInstanceOf(PaletteNotFoundException.class);

        then(deletePalettePort).should(never()).deletePalette(any());
    }

    public void givenPaletteExists(String code) {
        given(paletteExistsPort.paletteExists(code)).willReturn(true);
    }

    public void givenPaletteNotExists(String code) {
        given(paletteExistsPort.paletteExists(code)).willReturn(false);
    }
}