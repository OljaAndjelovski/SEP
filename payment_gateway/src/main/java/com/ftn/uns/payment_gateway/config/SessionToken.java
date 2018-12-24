package com.ftn.uns.payment_gateway.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SessionToken implements Authentication, Serializable {

    private Long sessionId;
    private String username;

    public SessionToken(Long sessionId, String username) {
        this.sessionId = sessionId;
        this.username = username;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public Object getCredentials() {
        return username;
    }

    @Override
    public Object getDetails() {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return new ArrayList<>();
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }

    @Override
    public Object getPrincipal() {
        return new Principal() {
            @Override
            public String getName() {
                return username;
            }
        };
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public boolean implies(Subject subject) {
        return true;
    }
}