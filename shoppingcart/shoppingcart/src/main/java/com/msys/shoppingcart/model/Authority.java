package com.msys.shoppingcart.model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@AllArgsConstructor
public class Authority implements GrantedAuthority {
    private final String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
