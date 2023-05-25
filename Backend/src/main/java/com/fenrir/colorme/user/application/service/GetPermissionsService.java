package com.fenrir.colorme.user.application.service;

import com.fenrir.colorme.common.annotation.UseCase;
import com.fenrir.colorme.common.security.AuthenticationFacade;
import com.fenrir.colorme.user.application.port.in.getpermissions.GetPermissionsUseCase;
import com.fenrir.colorme.user.application.port.in.getpermissions.GetPermissionsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@UseCase
@RequiredArgsConstructor
class GetPermissionsService implements GetPermissionsUseCase {
    private final AuthenticationFacade authenticationFacade;

    @Override
    public GetPermissionsResponse getPermissions() {
        final String userCode = authenticationFacade.getUserCode();
        final List<String> authorities = authenticationFacade.getAuthentication()
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return new GetPermissionsResponse(userCode, authorities);
    }
}
