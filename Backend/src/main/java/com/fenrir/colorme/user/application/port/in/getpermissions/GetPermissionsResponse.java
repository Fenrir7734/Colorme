package com.fenrir.colorme.user.application.port.in.getpermissions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class GetPermissionsResponse {

    @JsonProperty("userId")
    String code;
    List<String> permissions;
}
