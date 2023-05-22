package com.fenrir.colorme.palette.adapter.in.web;

import com.fenrir.colorme.palette.application.port.in.GetUserLikedPalettesCodesUseCase;
import com.fenrir.colorme.shared.WebAdapterTest;
import com.fenrir.colorme.shared.sercurity.AsUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserLikedPalettesCodesController.class)
class GetUserLikedPalettesCodesControllerTest extends WebAdapterTest {
    private static final String GET_USER_LIKED_PALETTES_CODES_CONTROLLER_ENDPOINT = "/api/v1/palettes/liked/codes";

    @MockBean
    private GetUserLikedPalettesCodesUseCase getUserLikedPalettesCodesUseCase;

    @Test
    @AsUser
    void testGetUserLikedPalettesCodesAsUser() throws Exception {
        // given
        final List<String> userLikedPalettesCodesResponse = givenResponse();

        givenGetUserLikedPalettesCodesUseCaseWillSuccess(userLikedPalettesCodesResponse);

        // when
        ResultActions response = mockMvc.perform(get(GET_USER_LIKED_PALETTES_CODES_CONTROLLER_ENDPOINT));

        // then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", equalTo(2)));
    }

    @Test
    void testGetUserLikedPalettesCodesAsAnonymousUser() throws Exception {
        // when
        ResultActions response = mockMvc.perform(get(GET_USER_LIKED_PALETTES_CODES_CONTROLLER_ENDPOINT));

        // then
        response.andExpect(status().isUnauthorized());

        then(getUserLikedPalettesCodesUseCase).should(never()).getUserLikedPalettesCodes();
    }

    private void givenGetUserLikedPalettesCodesUseCaseWillSuccess(List<String> response) {
        given(getUserLikedPalettesCodesUseCase.getUserLikedPalettesCodes()).willReturn(response);
    }

    private List<String> givenResponse() {
        return List.of("12345", "54321");
    }
}