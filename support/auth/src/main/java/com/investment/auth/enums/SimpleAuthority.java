package com.investment.auth.enums;

import org.springframework.security.core.GrantedAuthority;

public enum SimpleAuthority implements GrantedAuthority {

    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
