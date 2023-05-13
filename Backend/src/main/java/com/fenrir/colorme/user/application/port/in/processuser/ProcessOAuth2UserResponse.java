package com.fenrir.colorme.user.application.port.in.processuser;

import com.fenrir.colorme.user.domain.Role;
import lombok.Value;

@Value
public class ProcessOAuth2UserResponse {
    Long id;
    String email;
    Role role;
}
