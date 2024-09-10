package com.example.devopapi.api.auth;

import com.example.devopapi.api.user.validator.email.EmailUnique;
import com.example.devopapi.api.user.validator.password.Password;
import com.example.devopapi.api.user.validator.role.RoleIdConstrain;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record RegisterAuthDto(@NotBlank(message = "Email is required!")
                              @EmailUnique
                              @Email
                              String email,
                              @Password
                              @NotBlank(message = "Password is required!") String password,
                              @Password
                              @NotBlank(message = "ConfirmedPassword is required!") String confirmedPassword,
                              @NotNull(message = "Roles are required!")
                              @RoleIdConstrain
                              List<Integer> rolesIds) {
}
