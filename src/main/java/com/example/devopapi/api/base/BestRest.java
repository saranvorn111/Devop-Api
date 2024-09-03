package com.example.devopapi.api.base;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BestRest<T>(Boolean status,
                       Integer code,
                       String message,
                       LocalDateTime timestamp,
                       T data
                       ) {
}

