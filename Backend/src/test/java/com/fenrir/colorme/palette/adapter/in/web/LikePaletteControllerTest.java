package com.fenrir.colorme.palette.adapter.in.web;

import com.fenrir.colorme.palette.application.port.in.CreatePaletteLikeUseCase;
import com.fenrir.colorme.shared.WebAdapterTest;
import com.fenrir.colorme.shared.sercurity.AsUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LikePaletteController.class)
class LikePaletteControllerTest extends WebAdapterTest {
    private static final String LIKE_PALETTE_CONTROLLER_ENDPOINT = "/api/v1/palettes/%s/like";

    @MockBean
    private CreatePaletteLikeUseCase createPaletteLikeUseCase;

    @Test
    @AsUser
    void testLikePaletteAsUser() throws Exception {
        // given
        final String code = "1234";

        // when
        ResultActions response = mockMvc.perform(post(String.format(LIKE_PALETTE_CONTROLLER_ENDPOINT, code)));

        // then
        response.andExpect(status().isOk());

        then(createPaletteLikeUseCase).should().createLike(code);
    }

    @Test
    void testLikePaletteAsAnonymousUser() throws Exception {
        // given
        final String code = "1234";

        // when
        ResultActions response = mockMvc.perform(post(String.format(LIKE_PALETTE_CONTROLLER_ENDPOINT, code)));

        // then
        response.andExpect(status().isUnauthorized());

        then(createPaletteLikeUseCase).should(never()).createLike(any());
    }
}