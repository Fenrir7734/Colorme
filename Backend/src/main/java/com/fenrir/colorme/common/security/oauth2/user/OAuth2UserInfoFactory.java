package com.fenrir.colorme.common.security.oauth2.user;

import com.fenrir.colorme.common.security.exception.OAuth2AuthenticationProcessingException;
import com.fenrir.colorme.common.security.oauth2.OAuth2Provider;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        final OAuth2Provider provider = OAuth2Provider.fromRegistrationId(registrationId);
        return switch (provider) {
            case GOOGLE -> new GoogleOAuth2UserInfo(attributes);
            case FACEBOOK -> new FacebookOAuth2UserInfo(attributes);
            case GITHUB -> new GithubOAuth2UserInfo(attributes);
            case UNKNOWN -> throw new OAuth2AuthenticationProcessingException("Unknown OAuth2 provider");
        };
    }
}
