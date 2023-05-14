package com.fenrir.colorme.common.security;

import com.fenrir.colorme.common.security.oauth2.user.UserPrincipal;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationFacade {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
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
        Authentication authentication = getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getId().equals(userId);
    }
}
