package com.theWalkingDogsApp.demo.model.user;

import static com.theWalkingDogsApp.demo.model.user.Permission.ADMIN_CREATE;
import static com.theWalkingDogsApp.demo.model.user.Permission.ADMIN_DELETE;
import static com.theWalkingDogsApp.demo.model.user.Permission.ADMIN_READ;
import static com.theWalkingDogsApp.demo.model.user.Permission.ADMIN_UPDATE;
import static com.theWalkingDogsApp.demo.model.user.Permission.USER_CREATE;
import static com.theWalkingDogsApp.demo.model.user.Permission.USER_DELETE;
import static com.theWalkingDogsApp.demo.model.user.Permission.USER_READ;
import static com.theWalkingDogsApp.demo.model.user.Permission.USER_UPDATE;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
