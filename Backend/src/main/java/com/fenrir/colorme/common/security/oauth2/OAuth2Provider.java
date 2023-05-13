package com.fenrir.colorme.common.security.oauth2;

import java.util.Arrays;

public enum OAuth2Provider {
    GOOGLE,
    FACEBOOK,
    GITHUB,

    UNKNOWN;

    public static OAuth2Provider fromRegistrationId(String registrationId) {
        final OAuth2Provider[] providers = OAuth2Provider.values();
        return Arrays.stream(providers)
                .filter(provider -> registrationId.equalsIgnoreCase(provider.toString()))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
