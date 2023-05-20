package com.fenrir.colorme.common.security;

import com.fenrir.colorme.common.security.oauth2.ColormeOAuth2UserService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@Import({
        WebSecurityConfig.class,
        UnauthorizedEntryPoint.class,
        MethodSecurityConfig.class
})
@MockBean(ColormeOAuth2UserService.class)
public class SecurityTestConfig {
}
