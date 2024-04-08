package com.concertai.employeemanagement.security.token;

import com.concertai.employeemanagement.security.model.UserProfile;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.List;

public class CustomJwtToken extends JwtAuthenticationToken {

    private UserProfile userProfile;

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public CustomJwtToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities, UserProfile userProfile) {
        super(jwt, authorities);
        this.userProfile = userProfile;
    }

    @Override
    public Object getCredentials() {
        return super.getCredentials();
    }

    @Override
    public Object getPrincipal() {
        return userProfile;
    }


}
