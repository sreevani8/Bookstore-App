package com.mulit.bookstore.orders.domain;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {


    public String getLoginUserName() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();


        if(authentication == null){
            throw new RuntimeException("No authentication found");
        }


        if(authentication instanceof JwtAuthenticationToken jwtToken){

            Jwt jwt = jwtToken.getToken();

            String username =
                    jwt.getClaimAsString("preferred_username");


            if(username == null){
                username =
                        jwt.getClaimAsString("sub");
            }


            return username;
        }


        return authentication.getName();
    }
}