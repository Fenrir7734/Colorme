package com.fenrir.colorme.shared.sercurity;

import com.fenrir.colorme.common.security.oauth2.user.UserPrincipal;
import com.fenrir.colorme.user.domain.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;

public class AsUserSecurityContextFactory implements WithSecurityContextFactory<AsUser> {

    @Override
    public SecurityContext createSecurityContext(AsUser asUser) {
        final SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(createAuthentication(asUser));
        return context;
    }

    private Authentication createAuthentication(AsUser asUser) {
        final String email = getEmailFrom(asUser);
        final Collection<? extends GrantedAuthority> authorities = getAuthoritiesFrom(asUser);

        final UserPrincipal principal = UserPrincipal.create(asUser.id(), asUser.code(), email, asUser.password(), authorities);
        return new UsernamePasswordAuthenticationToken(
                principal,
                asUser.password(),
                principal.getAuthorities()
        );
    }

    private String getEmailFrom(AsUser asUser) {
        return StringUtils.hasLength(asUser.email())
                ? asUser.email()
                : asUser.value();
    }

    private Collection<? extends GrantedAuthority> getAuthoritiesFrom(AsUser asUser) {
        return Arrays.stream(asUser.roles())
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
