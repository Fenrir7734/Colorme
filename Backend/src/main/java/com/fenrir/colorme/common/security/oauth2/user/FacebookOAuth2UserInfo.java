package com.fenrir.colorme.common.security.oauth2.user;

import com.fenrir.colorme.common.security.oauth2.OAuth2Provider;

import java.util.Map;

public class FacebookOAuth2UserInfo extends OAuth2UserInfo {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";

    public FacebookOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get(ID);
    }

    @Override
    public String getName() {
        return (String) attributes.get(NAME);
    }

    @Override
    public String getEmail() {
        return (String) attributes.get(EMAIL);
    }

    @Override
    public OAuth2Provider getProvider() {
        return OAuth2Provider.FACEBOOK;
    }
}
