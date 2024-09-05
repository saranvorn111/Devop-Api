package com.example.devopapi.api.user;

import com.github.pagehelper.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface UserService {
    UserDto createNewsUser(CreateUserDto createUserDto);
//    Page<UserDto> findAllUser(Pageable pageable);

    List<UserDto> findAllUser();
    UserDto findUserById(Integer id);

    Integer deletedUserById(Integer id);

    Integer updateIsDeletedStatus(Integer id, boolean status);

}
