package com.fenrir.colorme.user.application.port.out;

import com.fenrir.colorme.user.domain.Provider;
import com.fenrir.colorme.user.domain.User;

import java.util.Optional;

public interface GetUserByProviderDataPort {
    Optional<User> getUser(Provider provider, String externalId);
}
