package com.mulit.bookstore.orders.domain;

// import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

/*@Service
public class SecurityService {

    public String getLoginUserName() {
         //return "user";

       JwtAuthenticationToken authentication =
                (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        OAuth2ResourceServerProperties.Jwt jwt = (OAuth2ResourceServerProperties.Jwt) authentication.getPrincipal();
        var username = jwt.getClaimAsString("preferred_username");
        var email = jwt.getClaimAsString("email");
        var name = jwt.getClaimAsString("name");
        var token = jwt.getTokenValue();
        var authorities = authentication.getAuthorities();


        return jwt.getClaimAsString("preferred_username");
    }
}*/

@Service
public class SecurityService {

    public String getLoginUserName() {

        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof JwtAuthenticationToken jwtAuth) {

            Jwt jwt = jwtAuth.getToken(); // ✅ correct object

            String username = jwt.getClaimAsString("preferred_username");

            return username;
        }

        return null;
    }
}
