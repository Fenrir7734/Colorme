package com.fenrir.colorme.palette.application.service;

import com.fenrir.colorme.common.security.AuthenticationFacade;
import com.fenrir.colorme.palette.application.port.out.CreatePaletteLikePort;
import com.fenrir.colorme.palette.application.port.out.GetPalettePort;
import com.fenrir.colorme.palette.application.port.out.PaletteLikeExistsPort;
import com.fenrir.colorme.palette.application.service.exception.PaletteNotFoundException;
import com.fenrir.colorme.palette.domain.Palette;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

class CreatePaletteLikeServiceTest {
    private final GetPalettePort getPalettePort = Mockito.mock(GetPalettePort.class);
    private final PaletteLikeExistsPort likeExistsPort = Mockito.mock(PaletteLikeExistsPort.class);
    private final CreatePaletteLikePort createLikePort = Mockito.mock(CreatePaletteLikePort.class);
    private final AuthenticationFacade authenticationFacade = Mockito.mock(AuthenticationFacade.class);

    private final CreatePaletteLikeService createPaletteLikeService = new CreatePaletteLikeService(getPalettePort, likeExistsPort, createLikePort, authenticationFacade);

    @Test
    void createNewLike() {
        // given
        final Long userId = 1L;
        final Palette palette = defaultPalette();

        givenLikeNotExists(palette.getCode(), userId);
        givenGetPaletteWillSuccess(palette);

        // when
        createPaletteLikeService.createLike(palette.getCode());

        // then
        then(createLikePort).should().createLike(any());
    }

    @Test
    void failsToCreateNewLikeIfUserAlreadyLikeThisPalette() {
        // given
        final Long userId = 1L;
        final String paletteCode = "1234";

        givenLikeAlreadyExists(paletteCode, userId);

        // when & then
        assertThatThrownBy(() -> createPaletteLikeService.createLike(paletteCode))
                .isInstanceOf(PaletteNotFoundException.class);

        then(createLikePort).should(never()).createLike(any());
    }

    @Test
    void failsToCreateNewLikeIfPaletteNotExists() {
        // given
        final Long userId = 1L;
        final Palette palette = defaultPalette();

        givenLikeNotExists(palette.getCode(), userId);
        givenGetPaletteWillFail(palette);

        // when & then
        assertThatThrownBy(() -> createPaletteLikeService.createLike(palette.getCode()))
                .isInstanceOf(PaletteNotFoundException.class);

        then(createLikePort).should(never()).createLike(any());
    }

    private void givenLikeNotExists(String paletteCode, Long userId) {
        given(likeExistsPort.likeExits(paletteCode, userId)).willReturn(false);
    }

    private void givenLikeAlreadyExists(String paletteCode, Long userId) {
        given(likeExistsPort.likeExits(paletteCode, userId)).willReturn(true);
    }

    private void givenGetPaletteWillSuccess(Palette palette) {
        given(getPalettePort.getPalette(palette.getCode())).willReturn(Optional.of(palette));
    }

    private void givenGetPaletteWillFail(Palette palette) {
        given(getPalettePort.getPalette(palette.getCode())).willReturn(Optional.empty());
    }

    private Palette defaultPalette() {
        final Palette palette = new Palette();
        palette.setId(1L);
        palette.setCode("1234");
        return palette;
    }
}