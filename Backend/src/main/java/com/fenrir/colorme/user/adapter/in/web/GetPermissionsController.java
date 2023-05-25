package com.fenrir.colorme.user.adapter.in.web;

import com.fenrir.colorme.common.annotation.WebAdapter;
import com.fenrir.colorme.common.security.annotation.AllowUser;
import com.fenrir.colorme.user.application.port.in.getpermissions.GetPermissionsResponse;
import com.fenrir.colorme.user.application.port.in.getpermissions.GetPermissionsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllowUser
@WebAdapter
@RestController
@RequestMapping(path = "/api/v1/me/permissions")
@RequiredArgsConstructor
public class GetPermissionsController {
    private final GetPermissionsUseCase getPermissionsUseCase;

    @GetMapping
    ResponseEntity<GetPermissionsResponse> getPermissions() {
        return ResponseEntity.ok(getPermissionsUseCase.getPermissions());
    }
}
