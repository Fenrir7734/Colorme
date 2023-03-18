package com.fenrir.colorme.palette.adapter.in.web;

import com.fenrir.colorme.palette.application.port.in.getpalette.GetPaletteResponse;
import com.fenrir.colorme.palette.application.port.in.getpalette.GetPaletteUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GetPaletteController.class)
class GetPaletteControllerTest {
    private static final String GET_PALETTE_CONTROLLER_ENDPOINT = "/api/v1/palettes/%s";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPaletteUseCase getPaletteUseCase;

    @Test
    void testGetPalette() throws Exception {
        // given
        final GetPaletteResponse getPaletteResponse = givenResponse();

        givenGetPaletteUseCaseWillSuccess(getPaletteResponse);

        // when
        ResultActions response = mockMvc.perform(get(String.format(GET_PALETTE_CONTROLLER_ENDPOINT, getPaletteResponse.getCode())));

        // then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(getPaletteResponse.getCode())))
                .andExpect(jsonPath("$.colors.size()", is(2)))
                .andExpect(jsonPath("$.colors[0]", is("123456")))
                .andExpect(jsonPath("$.colors[1]", is("ABCDEF")))
                .andExpect(jsonPath("$.tags.size()", is(1)))
                .andExpect(jsonPath("$.tags[0]", is(1)))
                .andExpect(jsonPath("$.likes", is(20)));
    }

    private void givenGetPaletteUseCaseWillSuccess(GetPaletteResponse response) {
        given(getPaletteUseCase.getPalette(response.getCode())).willReturn(response);
    }

    private GetPaletteResponse givenResponse() {
        return new GetPaletteResponse(
                "1234",
                List.of("123456", "ABCDEF"),
                List.of(1L),
                20
        );
    }
}