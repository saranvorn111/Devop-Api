package com.example.devopapi.api.auth;

import com.example.devopapi.api.user.validator.password.Password;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(@NotBlank
                       String email,
                       @NotBlank
                       @Password
                       String password) {
}
