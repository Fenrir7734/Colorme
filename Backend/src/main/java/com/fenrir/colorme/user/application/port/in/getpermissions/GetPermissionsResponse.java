package com.fenrir.colorme.user.application.port.in.getpermissions;

import lombok.Value;

import java.util.List;

@Value
public class GetPermissionsResponse {
    String code;
    List<String> permissions;
}
