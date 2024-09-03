package com.example.devopapi.api.auth;

import com.example.devopapi.api.base.BestRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auths")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/register")
    public BestRest<?> register(@RequestBody RegisterAuthDto registerAuthDto){
        authService.register(registerAuthDto);
        //need here call to service
        return BestRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You have been register successfully.")
                .timestamp(LocalDateTime.now())
                .data(registerAuthDto.email())
                .build();
    }

    @PostMapping("/verify")
    public BestRest<?> verify(@RequestParam String email){
        return BestRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Please check your email to verify.")
                .timestamp(LocalDateTime.now())
                .data(email)
                .build();
    }
}
