package com.example.devopapi.exception;

import com.example.devopapi.api.base.BastError;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityException {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public BastError<?> handleServiceException(AuthenticationException e) {
        return BastError.builder()
                .status(false)
                .code(HttpStatus.UNAUTHORIZED.value())
                .message("Authentication failed, please check your credentials")
                .errors(e.getMessage())
                .build();

    }}
