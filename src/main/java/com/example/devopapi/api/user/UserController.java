package com.example.devopapi.api.user;

import com.example.devopapi.api.base.BestRest;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public BestRest<?> insert(@RequestBody @Valid CreateUserDto createUserDto) {
        UserDto userDto = userService.createNewsUser(createUserDto);
                log.info("User = {}", createUserDto);
        return BestRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User insert success.")
                .data(userDto)
                .build();

    }
//    @GetMapping("")
//    public BestRest<?> findAllUser(@RequestParam(name = "page", required = false, defaultValue = "1") long page,
//                                   @RequestParam(name = "limit", required = false, defaultValue = "20") long limit){
//        return null;
//    }

    @GetMapping("")
    public BestRest<?> findAllUser(){
        return BestRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User find success.")
                .data(userService.findAllUser())
                .build();
    }

    @GetMapping("/{id}")
    public BestRest<?> findUserById(@PathVariable Integer id){
        UserDto userDto = userService.findUserById(id);
        return BestRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Use find success.")
                .data(userDto)
                .build();
    }

    @DeleteMapping("/{id}")
    public BestRest<?> deletedUserById(@PathVariable Integer id){
        Integer deleted = userService.deletedUserById(id);
        return BestRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User deleted success.")
                .data(deleted)
                .build();
    }

    @PutMapping("/{id}")
    public BestRest<?> updateIsDeletedById(@PathVariable Integer id, @RequestBody IsDeletedDto isDeletedDto ) {
        Integer isDeleted = userService.updateIsDeletedStatus(id, isDeletedDto.status());
        return BestRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("User is deleted success.")
                .data(isDeleted)
                .build();
    }
}
