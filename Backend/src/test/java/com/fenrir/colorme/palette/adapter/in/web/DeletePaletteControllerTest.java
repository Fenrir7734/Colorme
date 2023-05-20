package com.fenrir.colorme.palette.adapter.in.web;

import com.fenrir.colorme.palette.application.port.in.DeletePaletteUseCase;
import com.fenrir.colorme.shared.WebAdapterTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DeletePaletteController.class)
class DeletePaletteControllerTest extends WebAdapterTest {
    private static final String DELETE_PALETTE_CONTROLLER_ENDPOINT = "/api/v1/palettes/%s";

    @MockBean
    private DeletePaletteUseCase deletePaletteUseCase;

    @Test
    void testDeletePalette() throws Exception {
        // given
        final String paletteCode = "1234";

        // when
        ResultActions response = mockMvc.perform(delete(String.format(DELETE_PALETTE_CONTROLLER_ENDPOINT, paletteCode)));

        // then
        response.andExpect(status().isNoContent());

        then(deletePaletteUseCase).should().deletePalette(paletteCode);
    }
}