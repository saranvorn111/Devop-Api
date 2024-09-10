package com.example.devopapi.api.user;

import com.example.devopapi.api.auth.RegisterAuthDto;
import com.example.devopapi.api.user.web.CreateUserDto;
import com.example.devopapi.api.user.web.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapstruct {
    Users createUserDtoToUser(CreateUserDto createUserDto);
    UserDto UserToUserDto(Users users);

    Users UserDtoToUser(UserDto userDto);

    Users registerDtoToUer(RegisterAuthDto registerAuthDto);

}
