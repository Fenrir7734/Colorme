package com.fenrir.colorme.user.adapter.in.web;

import com.fenrir.colorme.shared.WebAdapterTest;
import com.fenrir.colorme.shared.sercurity.AsUser;
import com.fenrir.colorme.user.application.port.in.getpermissions.GetPermissionsResponse;
import com.fenrir.colorme.user.application.port.in.getpermissions.GetPermissionsUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GetPermissionsController.class)
class GetPermissionsControllerTest extends WebAdapterTest {
    private static final String GET_PERMISSIONS_CONTROLLER_ENDPOINT = "/api/v1/me/permissions";

    @MockBean
    private GetPermissionsUseCase getPermissionsUseCase;

    @Test
    @AsUser
    void testGetPermissionsAsUser() throws Exception {
        // given
        final GetPermissionsResponse getPermissionsResponse = permissionsResponse();

        givenGetPermissionsUserCaseWillSuccess(getPermissionsResponse);

        // when
        ResultActions response = mockMvc.perform(get(GET_PERMISSIONS_CONTROLLER_ENDPOINT));

        // then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(getPermissionsResponse.getCode())))
                .andExpect(jsonPath("$.permissions", is(getPermissionsResponse.getPermissions())));
    }

    @Test
    void testGetPermissionsAsAnonymousUser() throws Exception {
        // when
        ResultActions response = mockMvc.perform(get(GET_PERMISSIONS_CONTROLLER_ENDPOINT));

        // then
        response.andExpect(status().isUnauthorized());

        then(getPermissionsUseCase).should(never()).getPermissions();
    }

    private void givenGetPermissionsUserCaseWillSuccess(GetPermissionsResponse response) {
        given(getPermissionsUseCase.getPermissions()).willReturn(response);
    }

    private GetPermissionsResponse permissionsResponse() {
        return new GetPermissionsResponse(
                "1234",
                List.of("ROLE_USER")
        );
    }
}