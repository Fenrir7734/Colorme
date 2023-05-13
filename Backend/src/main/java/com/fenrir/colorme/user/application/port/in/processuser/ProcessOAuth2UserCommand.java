package com.fenrir.colorme.user.application.port.in.processuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ProcessOAuth2UserCommand {
    private String id;
    private String provider;
    private String name;
    private String email;
}
