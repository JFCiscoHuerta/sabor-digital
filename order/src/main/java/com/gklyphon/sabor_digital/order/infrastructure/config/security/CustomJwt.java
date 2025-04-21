package com.gklyphon.sabor_digital.order.infrastructure.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

/**
 * Custom JWT authentication token that extends {@link JwtAuthenticationToken}
 * to include additional user information such as username and email.
 *
 * @author JFCiscoHuerta
 * @date 2025/04/20
 */
public class CustomJwt extends JwtAuthenticationToken {

    private String username;
    private String email;

    /**
     * Constructs a new {@code CustomJwt} instance with the provided JWT and granted authorities.
     *
     * @param jwt the JWT token
     * @param authorities the granted authorities extracted from the token
     */
    public CustomJwt(Jwt jwt, Collection<? extends GrantedAuthority> authorities) {
        super(jwt, authorities);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
