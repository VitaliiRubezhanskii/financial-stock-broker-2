package com.investment.authservice.domain.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Authorities implements GrantedAuthority {
    ROLE_TRADER, ROLE_ADMIN, ROLE_BROKER;

    @Override
    public String getAuthority() {
        return name();
    }
}
