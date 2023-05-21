package com.fenrir.colorme.palette.adapter.in.web;

import com.fenrir.colorme.palette.application.port.in.DeletePaletteLikeUseCase;
import com.fenrir.colorme.shared.WebAdapterTest;
import com.fenrir.colorme.shared.sercurity.AsUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UnlikePaletteController.class)
class UnlikePaletteControllerTest extends WebAdapterTest {
    private static final String UNLIKE_PALETTE_CONTROLLER_ENDPOINT = "/api/v1/palettes/%s/unlike";

    @MockBean
    private DeletePaletteLikeUseCase deletePaletteLikeUseCase;

    @Test
    @AsUser
    void testUnlikePaletteAsUser() throws Exception {
        // given
        final String code = "1234";

        // when
        ResultActions response = mockMvc.perform(delete(String.format(UNLIKE_PALETTE_CONTROLLER_ENDPOINT, code)));

        // then
        response.andExpect(status().isNoContent());

        then(deletePaletteLikeUseCase).should().deleteLike(code);
    }

    @Test
    void testUnlikePaletteAsAnonymousUser() throws Exception {
        // given
        final String code = "1234";

        // when
        ResultActions response = mockMvc.perform(delete(String.format(UNLIKE_PALETTE_CONTROLLER_ENDPOINT, code)));

        // then
        response.andExpect(status().isUnauthorized());

        then(deletePaletteLikeUseCase).should(never()).deleteLike(any());
    }
}