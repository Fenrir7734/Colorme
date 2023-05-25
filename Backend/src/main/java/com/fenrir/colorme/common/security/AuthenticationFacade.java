package com.fenrir.colorme.common.security;

import com.fenrir.colorme.common.security.oauth2.user.UserPrincipal;
import com.fenrir.colorme.user.domain.Role;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationFacade {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUserId() {
        return getUserPrincipal().getId();
    }

    public String getUserCode() {
        return getUserPrincipal().getCode();
    }

    public boolean isAuthenticated() {
        Authentication authentication = getAuthentication();
        return authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);
    }

    public boolean isAuthenticated(Long userId) {
        return isAuthenticated() && isUserAuthenticated(userId);
    }

    public boolean isNotAuthenticated(Long userId) {
        return isAuthenticated() && !isUserAuthenticated(userId);
    }

    private boolean isUserAuthenticated(Long userId) {
        return getUserPrincipal().getId().equals(userId);
    }

    private UserPrincipal getUserPrincipal() {
        Authentication authentication = getAuthentication();
        return (UserPrincipal) authentication.getPrincipal();
    }

    public boolean isAdmin() {
        return isAuthenticated() && hasRole(Role.ADMIN.getName());
    }

    private boolean hasRole(String role) {
        return getUserPrincipal()
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(authority -> authority.equals(role));
    }
}
