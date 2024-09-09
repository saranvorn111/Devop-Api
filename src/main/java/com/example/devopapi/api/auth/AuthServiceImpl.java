package com.example.devopapi.api.auth;

import com.example.devopapi.api.base.BestRest;
import com.example.devopapi.api.user.UserMapstruct;
import com.example.devopapi.api.user.Users;
import com.example.devopapi.util.MailUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Value("${spring.mail.username}")
    private String appMail;

    private final AuthMapper authMapper;
    private final UserMapstruct userMapstruct;
    private final PasswordEncoder encoder;
    private final MailUtil mailUtil;

    @Transactional
    @Override
    public void register(RegisterAuthDto registerAuthDto) {
        Users users = userMapstruct.registerDtoToUer(registerAuthDto);
        users.setIsVerified(false);
//        users.setEmail(users.getEmail().toLowerCase());
        users.setPassword(encoder.encode(users.getPassword()));
        log.info("User: {}", users.getEmail());

        if (authMapper.register(users)) {
            // create user role
            for (Integer role : registerAuthDto.rolesIds()) {
                authMapper.createUserRole(users.getId(), role);
            }
        }
        authMapper.register(users);

    }

    @Override
    public void verify(String email) {

        Users users = authMapper.selectByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        String verifiedCode = (UUID.randomUUID().toString());

        if (authMapper.updateVerifiedCode(email, verifiedCode)) {
            users.setVerifiedCode(verifiedCode);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while updating verified code");
        }

        MailUtil.Meta<?> meta = MailUtil.Meta.builder()
                .to(email)
                .from(appMail)
                .subject("Verify your email")
                .templateUrl("/verify")
                .data(users)
                .build();

        try {
            mailUtil.sendMail(meta);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while sending email");
        }
    }

    @Override
    public Optional<Users> checkVerified(String email, String verifiedCode) {
        Users users = authMapper.selectByEmailAndVerifiedCode(email, verifiedCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (!users.getIsVerified()) {
            authMapper.verify(email, verifiedCode);
        }
        return Optional.empty();
    }
}
