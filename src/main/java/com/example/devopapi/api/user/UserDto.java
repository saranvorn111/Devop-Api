package com.example.devopapi.api.user;

import lombok.Builder;

@Builder
public record UserDto(String name,
                      String gender,
                      String studentCardId,
                      Boolean isStudent,
                      Boolean isDeleted) {
}
