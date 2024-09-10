package com.example.devopapi.api.user;

import com.example.devopapi.api.user.web.CreateUserDto;
import com.example.devopapi.api.user.web.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserMapstruct userMapstruct;

    @Override
    public UserDto createNewsUser(CreateUserDto createUserDto) {
        Users user = userMapstruct.createUserDtoToUser(createUserDto);
        userMapper.insert(user);
        return this.findUserById(user.getId());
    }

    @Override
    public List<UserDto> findAllUser() {
        List<UserDto> userDtoList = userMapper.select().stream()
                .map(userMapstruct::UserToUserDto)
                .collect(Collectors.toList());
        return userDtoList;
    }

//    @Override
//    public Page<UserDto> findAllUser(Pageable pageable) {
//        // Assuming userMapper.select(pageable) returns a Page<Users>
//        Page<Users> userPage = userMapper.select(pageable);
//
//        // Convert Users to UserDto
//        List<UserDto> userDtos = userPage.getResult().stream()
//                .map(user -> userMapstruct.UserToUserDto(user))
//                .collect(Collectors.toList());
//
//        // Return a new PageImpl containing UserDto objects
//        return null;
//    }

    @Override
    public UserDto findUserById(Integer id) {
        Users users = userMapper.selectById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with %d not found", id)
                  ));
        return userMapstruct.UserToUserDto(users);
    }

    @Override
    public Integer deletedUserById(Integer id) {
        boolean isExisted = userMapper.exitsById(id);
        if(isExisted){
            userMapper.deletedById(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d not found", id)
        );
    }

    @Override
    public Integer updateIsDeletedStatus(Integer id,boolean status) {
        boolean isExisted = userMapper.exitsById(id);
        if(isExisted){
            userMapper.updateIsDeletedById(id, status);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d not found", id)
        );
    }
}
