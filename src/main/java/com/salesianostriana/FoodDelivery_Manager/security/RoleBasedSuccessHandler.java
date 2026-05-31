package com.salesianostriana.FoodDelivery_Manager.security;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RoleBasedSuccessHandler implements AuthenticationSuccessHandler {

    private static final Map<String, Integer> PESO = Map.of(
        "ROLE_ADMIN",    10,
        "ROLE_OPERADOR",  1
    );

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication auth) throws IOException {
        String rol = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(a -> a.startsWith("ROLE_"))
                .sorted((r1, r2) ->
                        PESO.getOrDefault(r2, 0) - PESO.getOrDefault(r1, 0))
                .findFirst()
                .orElse("ROLE_DESCONOCIDO");

        String destino = switch (rol) {
            case "ROLE_ADMIN"    -> "/";
            case "ROLE_OPERADOR" -> "/";
            default              -> "/login?error=rolDesconocido";
        };

        new DefaultRedirectStrategy()
                .sendRedirect(request, response, destino);
    }
}