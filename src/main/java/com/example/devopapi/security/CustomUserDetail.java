package com.example.devopapi.security;

import com.example.devopapi.api.user.Users;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
@Builder
public class CustomUserDetail implements UserDetails {
    private Users users;

    //role and permission
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return users.getRoles();
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getEmail();
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
}
