package com.example.devopapi.api.auth;

import com.example.devopapi.api.base.BestRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auths")
@RequiredArgsConstructor
@Slf4j
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/login")
    public BestRest<?> login(@Valid @RequestBody LoginDto loginDto){
        AuthDto authDto = authService.login(loginDto);
        return BestRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You have been login successfully.")
                .timestamp(LocalDateTime.now())
                .data(authDto)
                .build();
    }

    @PostMapping("/register")
    public BestRest<?> register( @Valid @RequestBody RegisterAuthDto registerAuthDto){
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
        authService.verify(email);
        return BestRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Please check your email to verify.")
                .timestamp(LocalDateTime.now())
                .data(email)
                .build();
    }

    @GetMapping("/check-verify")
    public BestRest<?> checkVerify(@RequestParam String email, @RequestParam String verifiedCode){

        log.info("Email: {}", email);
        log.info("VerifiedCode: {}", verifiedCode);
        authService.checkVerified(email, verifiedCode);
        return BestRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You have been verified successfully.")
                .timestamp(LocalDateTime.now())
                .data(email)
                .build();
    }
}
