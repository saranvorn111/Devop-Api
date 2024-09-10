package com.example.devopapi.api.user.web;

import lombok.Builder;

@Builder
public record UserDto(String name,
                      String gender,
                      String studentCardId,
                      boolean isStudent,
                      boolean isDeleted) {
}
