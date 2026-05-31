package com.salesianostriana.FoodDelivery_Manager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.savedrequest.NullRequestCache;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .csrfTokenRepository(new HttpSessionCsrfTokenRepository())
                        .ignoringRequestMatchers("/h2-console/**", "/h2/**"))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/", "/index", "/error", "/css/**", "/js/**", "/img/**", "/h2-console/**",
                                "/h2/**")
                        .permitAll()
                        .requestMatchers("/repartidores/nuevoRepartidor/**").hasAnyRole("ADMIN", "OPERADOR")
                        .requestMatchers("/pedidos/nuevoPedido/**").hasAnyRole("ADMIN", "OPERADOR")
                        .requestMatchers("/entregas/nuevaEntrega/**", "/entregas/asignarPedido/**")
                        .hasAnyRole("ADMIN", "OPERADOR")

                        .requestMatchers("/repartidores/editarRepartidor/**", "/repartidores/borrarRepartidor/**")
                        .hasRole("ADMIN")
                        .requestMatchers("/pedidos/editarPedido/**", "/pedidos/borrarPedido/**").hasRole("ADMIN")
                        .requestMatchers("/entregas/editarEntrega/**", "/entregas/borrarEntrega/**").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/operador/**").hasRole("OPERADOR")
                        .anyRequest().authenticated())
                .requestCache(c -> c.requestCache(new NullRequestCache()))
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(authenticationSuccessHandler)
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll())
                .headers(h -> h.frameOptions(opts -> opts.disable()));

        return http.build();
    }
}