package com.example.devopapi.security;

import com.example.devopapi.api.auth.AuthMapper;
import com.example.devopapi.api.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthMapper authMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = authMapper.loadUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        CustomUserDetail customUserDetail = new CustomUserDetail();
        customUserDetail.setUsers(users);
        return customUserDetail;
    }
}
