package com.example.devopapi.api.auth;

import com.example.devopapi.api.user.UserMapstruct;
import com.example.devopapi.api.user.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthMapper authMapper;
    private final UserMapstruct userMapstruct;
    private final PasswordEncoder encoder;
    @Override
    public void register(RegisterAuthDto registerAuthDto) {
        Users users = userMapstruct.registerDtoToUer(registerAuthDto);
        users.setIsVerified(false);
        users.setPassword(encoder.encode(users.getPassword()));
        log.info("User: {}", users.getEmail());
        authMapper.register(users);

    }

    @Override
    public void verify(String email) {

    }
}
