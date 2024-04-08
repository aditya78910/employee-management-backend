package com.concertai.employeemanagement.security.converter;

import com.concertai.employeemanagement.security.model.UserProfile;
import com.concertai.employeemanagement.security.token.CustomJwtToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TokenConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Autowired
    ObjectMapper objectMapper;


    public AbstractAuthenticationToken convert(Jwt jwt) {

        Map<String, Object> claims = jwt.getClaims();
        LinkedTreeMap l = (LinkedTreeMap) claims.get("realm_access");
        List<String> rolesList = (List<String>)l.get("roles");

        UserProfile userProfile = objectMapper.convertValue(claims, UserProfile.class);

        List<SimpleGrantedAuthority> authorities = rolesList.stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                .collect(Collectors.toList());

        return new CustomJwtToken(jwt, authorities, userProfile);
    }

}
