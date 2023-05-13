package com.fenrir.colorme.user.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Provider {
    GOOGLE,
    FACEBOOK;

    public static Optional<Provider> getProvider(String name) {
        final Provider[] providers = Provider.values();
        return Arrays.stream(providers)
                .filter(provider -> name.equalsIgnoreCase(provider.toString()))
                .findFirst();
    }
}
