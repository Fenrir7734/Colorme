package com.fenrir.colorme.user.application.port.out;

import com.fenrir.colorme.user.domain.User;

public interface SaveUserPort {
    User saveUser(User user);
}
