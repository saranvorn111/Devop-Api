package com.example.devopapi.api.auth;

public interface AuthService {

    void register(RegisterAuthDto registerAuthDto);
    void verify(String email);
}
