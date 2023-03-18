package com.fenrir.colorme.palette.application.service;

import com.fenrir.colorme.palette.application.port.in.getpalette.GetPaletteResponse;
import com.fenrir.colorme.palette.application.port.out.GetPalettePort;
import com.fenrir.colorme.palette.application.service.exception.PaletteNotFoundException;
import com.fenrir.colorme.palette.domain.Palette;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

class GetPaletteServiceTest {
    private final GetPalettePort getPalettePort = Mockito.mock(GetPalettePort.class);
    private final PaletteMapper paletteMapper = Mockito.mock(PaletteMapper.class);

    private final GetPaletteService getPaletteService = new GetPaletteService(getPalettePort, paletteMapper);

    @Test
    void getsPalette() {
        // given
        final Palette palette = givenPalette();
        final GetPaletteResponse response = toGetPaletteResponse(palette);

        givenGetPaletteWillSuccess(palette);
        givenMappingToGetPaletteResponseWillSuccess(palette, response);

        // when
        final GetPaletteResponse actualResponse = getPaletteService.getPalette(palette.getCode());

        // then
        assertThat(actualResponse).isEqualTo(response);
    }

    @Test
    void getsPaletteFailsDueToInvalidPaletteCode() {
        // given
        final Palette palette = givenPalette();
        final GetPaletteResponse response = toGetPaletteResponse(palette);

        givenGetPaletteWillFail(palette);
        givenMappingToGetPaletteResponseWillSuccess(palette, response);

        // when & then
        assertThatThrownBy(() -> getPaletteService.getPalette(palette.getCode()))
                .isInstanceOf(PaletteNotFoundException.class);
    }

    private void givenGetPaletteWillSuccess(Palette palette) {
        given(getPalettePort.getPalette(palette.getCode())).willReturn(Optional.of(palette));
    }

    private void givenGetPaletteWillFail(Palette palette) {
        given(getPalettePort.getPalette(palette.getCode())).willReturn(Optional.empty());
    }

    private void givenMappingToGetPaletteResponseWillSuccess(Palette palette, GetPaletteResponse response) {
        given(paletteMapper.toGetPaletteResponse(palette)).willReturn(response);
    }

    private GetPaletteResponse toGetPaletteResponse(Palette palette) {
        return new GetPaletteResponse(
                palette.getCode(),
                palette.getHexList(),
                palette.getTags(),
                palette.likesCount()
        );
    }

    private Palette givenPalette() {
        Palette palette = new Palette();
        palette.setId(1L);
        palette.setCode("1234");
        return palette;
    }
}