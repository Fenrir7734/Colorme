package com.fenrir.colorme.user.application.service;

import com.fenrir.colorme.common.security.AuthenticationFacade;
import com.fenrir.colorme.user.application.port.in.getpermissions.GetPermissionsResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.mockito.BDDMockito.given;

class GetPermissionsServiceTest {
    private final AuthenticationFacade authenticationFacade = Mockito.mock(AuthenticationFacade.class);

    private final GetPermissionsService getPermissionsService = new GetPermissionsService(authenticationFacade);

    @Test
    void getsPermissions() {
        // given
        final String userCode = "1234";
        final String role = "ROLE_USER";

        givenGetUserCodeWillSuccess(userCode);
        givenGetAuthenticationWillSuccess(role);

        // when
        final GetPermissionsResponse response = getPermissionsService.getPermissions();

        // then
        assertThat(response)
                .hasNoNullFieldsOrProperties()
                .returns(userCode, from(GetPermissionsResponse::getCode))
                .returns(List.of(role), from(GetPermissionsResponse::getPermissions));
    }

    private void givenGetUserCodeWillSuccess(String userCode) {
        given(authenticationFacade.getUserCode()).willReturn(userCode);
    }

    private void givenGetAuthenticationWillSuccess(String role) {
        Authentication authentication = Mockito.mock(Authentication.class);
        Collection authorities = List.of(new SimpleGrantedAuthority(role));
        given(authentication.getAuthorities()).willReturn(authorities);
        given(authenticationFacade.getAuthentication()).willReturn(authentication);
    }
}