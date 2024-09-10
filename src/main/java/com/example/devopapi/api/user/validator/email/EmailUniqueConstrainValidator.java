package com.example.devopapi.api.user.validator.email;

import com.example.devopapi.api.user.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailUniqueConstrainValidator implements ConstraintValidator<EmailUnique, String> {

    private final UserMapper userMapper;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userMapper.exitsByEmail(email);
    }
}
