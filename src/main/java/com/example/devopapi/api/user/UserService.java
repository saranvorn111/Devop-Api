package com.example.devopapi.api.user;

import com.example.devopapi.api.user.web.CreateUserDto;
import com.example.devopapi.api.user.web.UserDto;

import java.util.List;

public interface UserService {
    UserDto createNewsUser(CreateUserDto createUserDto);
//    Page<UserDto> findAllUser(Pageable pageable);

    List<UserDto> findAllUser();
    UserDto findUserById(Integer id);

    Integer deletedUserById(Integer id);

    Integer updateIsDeletedStatus(Integer id, boolean status);

}
