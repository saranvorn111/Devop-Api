package com.example.devopapi.api.user.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateUserDto(@NotBlank(message = "Name is required") String name,
                            @NotBlank(message = "Gender is required") String gender,
                            String studentCardId,
                            String onesignalId,
                            @NotNull(message = "You have to confirm, are you a student!") Boolean isStudent
                            ) {
}
