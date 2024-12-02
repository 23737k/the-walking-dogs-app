package com.theWalkingDogsApp.demo.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.theWalkingDogsApp.demo.model.user.Permission.*;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("ADMIN",
            Set.of(
                    ADMIN_READ,
                    ADMIN_CREATE,
                    ADMIN_UPDATE,
                    ADMIN_DELETE
            )),
    USER("USER",
            Set.of(
                    USER_READ,
                    USER_CREATE,
                    USER_UPDATE,
                    USER_DELETE
            )
            );

    private final String role;
    private final Set<Permission> permissions;


    public SimpleGrantedAuthority getRole(){
        return new SimpleGrantedAuthority("ROLE_" + role);
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(getRole());
        authorities.addAll(permissions.stream().map(p ->
                new SimpleGrantedAuthority(p.getPermission())
        ).collect(Collectors.toSet()));
        return authorities;
    }
}
