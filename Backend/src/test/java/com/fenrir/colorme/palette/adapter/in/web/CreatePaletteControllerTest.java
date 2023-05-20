package com.fenrir.colorme.palette.adapter.in.web;

import com.fenrir.colorme.palette.application.port.in.createpalette.CreatePaletteCommand;
import com.fenrir.colorme.palette.application.port.in.createpalette.CreatePaletteResponse;
import com.fenrir.colorme.palette.application.port.in.createpalette.CreatePaletteUseCase;
import com.fenrir.colorme.shared.WebAdapterTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CreatePaletteController.class)
class CreatePaletteControllerTest extends WebAdapterTest {
    private static final String CREATE_PALETTE_CONTROLLER_ENDPOINT = "/api/v1/palettes";

    @MockBean
    private CreatePaletteUseCase createPaletteUseCase;

    @Test
    void testCreatePalette() throws Exception {
        // given
        final CreatePaletteCommand command = givenCommand();
        final CreatePaletteResponse createPaletteResponse = toCreatePaletteResponse(command);

        givenCreatePaletteUseCaseWillSuccess(command, createPaletteResponse);

        // when
        ResultActions response = mockMvc.perform(post(CREATE_PALETTE_CONTROLLER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(command)));

        // then
        response.andExpect(status().isCreated())
                .andExpect(header().string("Location", String.format("/api/v1/palettes/%s", createPaletteResponse.getCode())))
                .andExpect(jsonPath("$.code", is(createPaletteResponse.getCode())))
                .andExpect(jsonPath("$.colors.size()", is(2)))
                .andExpect(jsonPath("$.colors[0]", is("123456")))
                .andExpect(jsonPath("$.colors[1]", is("ABCDEF")))
                .andExpect(jsonPath("$.tags.size()", is(0)));
    }

    @Test
    void testCreatePaletteValidation() throws Exception {
        // given
        final CreatePaletteCommand command = givenCommand();
        command.setColors(null);

        // when
        ResultActions response = mockMvc.perform(post(CREATE_PALETTE_CONTROLLER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(command)));

        // then
        response.andExpect(status().isBadRequest());

        then(createPaletteUseCase).should(never()).createPalette(any());
    }

    private void givenCreatePaletteUseCaseWillSuccess(CreatePaletteCommand command, CreatePaletteResponse response) {
        given(createPaletteUseCase.createPalette(command)).willReturn(response);
    }

    private CreatePaletteResponse toCreatePaletteResponse(CreatePaletteCommand command) {
        return new CreatePaletteResponse(
                "1234",
                command.getColors(),
                command.getTags()
        );
    }

    private CreatePaletteCommand givenCommand() {
        CreatePaletteCommand command = new CreatePaletteCommand();
        command.setColors(List.of("123456", "ABCDEF"));
        command.setTags(new ArrayList<>());
        return command;
    }
}