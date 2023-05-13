package com.fenrir.colorme.common.security.oauth2;

import com.fenrir.colorme.common.security.exception.OAuth2AuthenticationProcessingException;
import com.fenrir.colorme.common.security.oauth2.user.OAuth2UserInfo;
import com.fenrir.colorme.common.security.oauth2.user.OAuth2UserInfoFactory;
import com.fenrir.colorme.common.security.oauth2.user.UserPrincipal;
import com.fenrir.colorme.user.application.port.in.processuser.ProcessOAuth2UserCommand;
import com.fenrir.colorme.user.application.port.in.processuser.ProcessOAuth2UserResponse;
import com.fenrir.colorme.user.application.port.in.processuser.ProcessOAuth2UserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class ColormeOAuth2UserService extends DefaultOAuth2UserService {
    private static final String EMAIL_ADDRESS_EXCEPTION = "Could not extract email address from OAuth2 provider";

    private final ProcessOAuth2UserUseCase processOAuth2UserUseCase;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        return processOAuth2User(userRequest, oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        final OAuth2UserInfo oAuth2UserInfo = toOAuth2UserInfo(oAuth2UserRequest, oAuth2User);
        validateOAuth2UserInfo(oAuth2UserInfo);
        ProcessOAuth2UserResponse response = processOAuth2UserUseCase.processUser(toCommand(oAuth2UserInfo));
        return UserPrincipal.create(response, oAuth2UserInfo.getAttributes());
    }

    private OAuth2UserInfo toOAuth2UserInfo(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        final String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();
        final Map<String, Object> attributes = oAuth2User.getAttributes();
        return toOAuth2UserInfo(registrationId, attributes);
    }

    private OAuth2UserInfo toOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        try {
            return OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
        } catch (OAuth2AuthenticationProcessingException e) {
            log.warn(String.format("Provider = %s. %s", registrationId, e.getMessage()));
            throw e;
        }
    }

    private void validateOAuth2UserInfo(OAuth2UserInfo oAuth2UserInfo) {
        if (oAuth2UserInfo.getEmail() == null) {
            log.warn(String.format("User = %s provider = %s. %s", oAuth2UserInfo.getId(), oAuth2UserInfo.getProvider(), EMAIL_ADDRESS_EXCEPTION));
            throw new OAuth2AuthenticationProcessingException(EMAIL_ADDRESS_EXCEPTION);
        }
    }

    private ProcessOAuth2UserCommand toCommand(OAuth2UserInfo oAuth2UserInfo) {
        return ProcessOAuth2UserCommand.builder()
                .id(oAuth2UserInfo.getId())
                .provider(oAuth2UserInfo.getProvider().toString())
                .name(oAuth2UserInfo.getName())
                .email(oAuth2UserInfo.getEmail())
                .build();
    }
}
