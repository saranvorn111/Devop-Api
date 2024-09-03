package com.example.devopapi.api.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RegisterAuthDto(@NotBlank(message = "Email is required") String email,
                              @NotBlank(message = "Password is required!") String password,
                              @NotBlank(message = "ConfirmedPassword is required!") String confirmedPassword,
                              @NotNull(message = "Roles are required!")
                              List<Integer> roles) {
}
