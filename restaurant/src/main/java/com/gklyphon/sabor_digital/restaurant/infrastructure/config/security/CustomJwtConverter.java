package com.gklyphon.sabor_digital.restaurant.infrastructure.config.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom converter that transforms a Jwt object into a {@link CustomJwt} authentication token.
 * It extracts user roles from the JWT claims and attaches additional information like email and username.
 *
 * @author JFCiscoHuerta
 * @date 2025/04/19
 */
public class CustomJwtConverter implements Converter<Jwt, CustomJwt> {

    /**
     * Converts the given Jwt into a {@link CustomJwt} authentication token,
     * extracting roles and custom user information from claims.
     *
     * @param jwt the JWT token to convert
     * @return the corresponding CustomJwt authentication token
     */
    @Override
    public CustomJwt convert(Jwt jwt) {
        List<GrantedAuthority> grantedAuthorities = extractAuthorities(jwt);
        var customJwt = new CustomJwt(jwt, grantedAuthorities);
        customJwt.setEmail(jwt.getClaimAsString("email"));
        customJwt.setUsername(jwt.getClaimAsString("preferred_username"));

        return customJwt;
    }

    /**
     * Extracts granted authorities (roles) from the "realm_access" claim of the JWT.
     *
     * @param jwt the JWT token from which to extract roles
     * @return a list of {@link GrantedAuthority} based on roles found in the token
     */
    private List<GrantedAuthority> extractAuthorities(Jwt jwt) {
        var result = new ArrayList<GrantedAuthority>();
        var realm_access = jwt.getClaimAsMap("realm_access");

        if (realm_access != null && realm_access.get("roles") != null) {
            var roles = realm_access.get("roles");
            if (roles instanceof List l) {
                l.forEach( role -> {
                    result.add(new SimpleGrantedAuthority("ROLE_" + role));
                });
            }
        }

        return result;
    }
}

