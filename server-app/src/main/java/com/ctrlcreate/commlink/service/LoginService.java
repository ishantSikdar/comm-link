package com.ctrlcreate.commlink.service;

import com.ctrlcreate.commlink.dto.login.LoginRequest;
import com.ctrlcreate.commlink.dto.login.LoginResponse;
import com.ctrlcreate.commlink.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final DBService dbService;

    public LoginResponse processLogin(LoginRequest loginRequest) {

        User user = dbService.findUserByEmail(loginRequest.getEmail());
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPwdHash())) {

            user.setLastLoginAt(Instant.now());
            dbService.saveUser(user);

            return LoginResponse.builder()
                    .authToken(jwtService.generateToken(user))
                    .build();

        } else {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}
