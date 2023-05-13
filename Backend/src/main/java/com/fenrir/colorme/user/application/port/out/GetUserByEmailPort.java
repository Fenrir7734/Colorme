package com.fenrir.colorme.user.application.port.out;

import com.fenrir.colorme.user.domain.User;

import java.util.Optional;

public interface GetUserByEmailPort {
    Optional<User> getUser(String email);
}
