package com.gklyphon.sabor_digital.table.infrastructure.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

/**
 * Security configuration class for the Table microservice.
 * Configures HTTP security, CORS, and OAuth2 resource server with JWT support.
 *
 * @author JFCiscoHuerta
 * @date 2025/04/20
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_ENDPOINTS = {};

    private static final String[] AUTHENTICATED_ENDPOINTS = {
            "/api/tables", "/api/tables/**", "/api/tables/by-ids",
            "/api/tables/all-by-restaurant/**"
    };

    /**
     * Configures the security filter chain.
     * Defines endpoint access rules, enables stateless session, CORS, CSRF,
     * and configures the resource server to use JWT authentication.
     *
     * @param http the HttpSecurity to modify
     * @return the configured SecurityFilterChain
     * @throws Exception in case of configuration errors
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                (authz) -> authz
                        .requestMatchers(AUTHENTICATED_ENDPOINTS).hasRole("USER")
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer( (oauth2) -> oauth2.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(customJwtConverter())))
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors( cors -> cors.configurationSource(corsConfigurationSource()) )
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    /**
     * Registers a custom JWT converter used to transform a Jwt into an AuthenticationToken.
     *
     * @return the custom JWT converter
     */
    @Bean
    Converter<Jwt, ? extends AbstractAuthenticationToken> customJwtConverter() {
        return new CustomJwtConverter();
    }

    /**
     * Configures CORS settings for the application.
     * Allows specific origins, methods, and headers.
     *
     * @return the configured CorsConfigurationSource
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}