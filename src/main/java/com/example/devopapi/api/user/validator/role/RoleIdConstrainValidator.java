package com.example.devopapi.api.user.validator.role;

import com.example.devopapi.api.user.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RoleIdConstrainValidator implements ConstraintValidator<RoleIdConstrain, List<Integer>>{

    private final UserMapper userMapper;

    @Override
    public boolean isValid(List<Integer> roleIds, ConstraintValidatorContext context) {

        for(Integer roleId : roleIds){
            if(!userMapper.checkRoleById(roleId)){
                return false;
            }
        }
        return true;
    }
}
