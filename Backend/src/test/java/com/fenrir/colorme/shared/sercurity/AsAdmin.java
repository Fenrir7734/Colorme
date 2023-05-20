package com.fenrir.colorme.shared.sercurity;

import com.fenrir.colorme.user.domain.Role;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@AsUser(roles = Role.ADMIN)
public @interface AsAdmin { }
