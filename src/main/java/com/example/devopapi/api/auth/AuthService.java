package com.example.devopapi.api.auth;

import com.example.devopapi.api.user.Users;

import java.util.Optional;

public interface AuthService {

    void register(RegisterAuthDto registerAuthDto);

    void verify(String email);

    Optional<Users> checkVerified(String email, String verifiedCode);
}
