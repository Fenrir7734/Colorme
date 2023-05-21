package com.fenrir.colorme.palette.application.service;

import com.fenrir.colorme.common.security.AuthenticationFacade;
import com.fenrir.colorme.palette.application.port.in.createpalette.CreatePaletteCommand;
import com.fenrir.colorme.palette.application.port.in.createpalette.CreatePaletteResponse;
import com.fenrir.colorme.palette.application.port.out.CreatePalettePort;
import com.fenrir.colorme.palette.application.service.exception.TagsNotFoundException;
import com.fenrir.colorme.palette.domain.Palette;
import com.fenrir.colorme.palette.domain.PaletteColor;
import com.fenrir.colorme.tag.application.port.in.ExistsAllTagsQuery;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

class CreatePaletteServiceTest {
    private final CreatePalettePort createPalettePort = Mockito.mock(CreatePalettePort.class);
    private final ExistsAllTagsQuery existsAllTagsQuery = Mockito.mock(ExistsAllTagsQuery.class);
    private final PaletteMapper paletteMapper = Mockito.mock(PaletteMapper.class);
    private final AuthenticationFacade authenticationFacade = Mockito.mock(AuthenticationFacade.class);

    private final CreatePaletteService createPaletteService = new CreatePaletteService(createPalettePort, existsAllTagsQuery, paletteMapper, authenticationFacade);

    @Test
    void createsNewColorPalette() {
        // given
        final CreatePaletteCommand command = givenCommand();
        final Palette palette = toPaletteDomainObject(command);
        final CreatePaletteResponse response = toCreatePaletteResponse(palette);

        givenMappingToPaletteDomainObjectWillSucceed(command, palette);
        givenValidatingTagsWillSucceeds(palette);
        givenMappingToResponseWillSucceeds(palette, response);

        // when
        final CreatePaletteResponse actualResponse = createPaletteService.createPalette(command);

        // then
        assertThat(actualResponse).isEqualTo(response);

        then(createPalettePort).should().createPalette(palette);
    }

    @Test
    void createsNewColorPaletteFailDueToInvalidTags() {
        // given
        final CreatePaletteCommand command = givenCommand();
        final Palette palette = toPaletteDomainObject(command);
        final CreatePaletteResponse response = toCreatePaletteResponse(palette);

        givenMappingToPaletteDomainObjectWillSucceed(command, palette);
        givenValidatingTagsWillFail(palette);
        givenMappingToResponseWillSucceeds(palette, response);

        // when & then
        assertThatThrownBy(() -> createPaletteService.createPalette(command))
                .isInstanceOf(TagsNotFoundException.class);

        then(createPalettePort).should(never()).createPalette(any());
    }

    private void givenMappingToPaletteDomainObjectWillSucceed(CreatePaletteCommand command, Palette palette) {
        given(paletteMapper.toPalette(command)).willReturn(palette);
    }

    private void givenValidatingTagsWillSucceeds(Palette palette) {
        given(existsAllTagsQuery.existsAllTags(palette.getTags())).willReturn(true);
    }

    private void givenValidatingTagsWillFail(Palette palette) {
        given(existsAllTagsQuery.existsAllTags(palette.getTags())).willReturn(false);
    }

    private void givenMappingToResponseWillSucceeds(Palette palette, CreatePaletteResponse response) {
        given(paletteMapper.toCreatePaletteResponse(palette)).willReturn(response);
    }

    private CreatePaletteResponse toCreatePaletteResponse(Palette palette) {
        return new CreatePaletteResponse(
                "Code",
                palette.getHexList(),
                palette.getTags());
    }

    private Palette toPaletteDomainObject(CreatePaletteCommand command) {
        Palette palette = new Palette();
        palette.setTags(command.getTags());

        List<PaletteColor> colors = new ArrayList<>();
        for (String color : command.getColors()) {
            colors.add(new PaletteColor(null, color));
        }
        palette.setColors(colors);

        return palette;
    }

    private CreatePaletteCommand givenCommand() {
        CreatePaletteCommand command = new CreatePaletteCommand();
        command.setColors(List.of("123456", "654321"));
        command.setTags(List.of(1L, 2L, 3L));
        return command;
    }
}