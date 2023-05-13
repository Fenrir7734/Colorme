package com.fenrir.colorme.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String code;
    private String externalId;
    private String externalName;
    private String email;
    private Provider provider;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
