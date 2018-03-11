package ua.vadim.blog.service;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.jetty.core.AbstractKeycloakJettyAuthenticator;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class TokenManager {

    @Bean
    @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AccessToken getAccessToken() {
        HttpServletRequest request = new RequestManager().getServletRequest();
        return ((KeycloakPrincipal) request.getUserPrincipal())
                .getKeycloakSecurityContext().getToken();
    }

    public String getUsername(){
        AccessToken token = getAccessToken();
        return token.getPreferredUsername();
    }
}
