package com.fenrir.colorme.common.security.oauth2.user;

import com.fenrir.colorme.common.security.oauth2.OAuth2Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public abstract class OAuth2UserInfo {
    protected final Map<String, Object> attributes;

    public abstract String getId();
    public abstract String getName();
    public abstract String getEmail();
    public abstract OAuth2Provider getProvider();
}
