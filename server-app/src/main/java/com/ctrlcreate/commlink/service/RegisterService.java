package com.ctrlcreate.commlink.service;

import com.ctrlcreate.commlink.dto.register.RegisterRequest;
import com.ctrlcreate.commlink.model.user.User;
import com.ctrlcreate.commlink.model.user.pojo.Profile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterService {

    private final PasswordEncoder passwordEncoder;
    private final DBService dbService;

    public void processUserRegistration(
            RegisterRequest registerRequest
    ) {
        User newUser = dbService.saveUser(
                User.builder()
                    .username(registerRequest.getUsername())
                    .type(registerRequest.getType())
                    .email(registerRequest.getEmail())
                    .password(registerRequest.getPassword())
                    .pwdHash(passwordEncoder.encode(registerRequest.getPassword()))
                        .profile(Profile.builder()
                                .name(registerRequest.getName())
                                .build())
                    .createdAt(Instant.now())
                    .build()
        );

        log.info("New User Saved: {}", newUser.getId());
    }
}
