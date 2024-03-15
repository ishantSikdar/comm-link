package com.ctrlcreate.commlink.service;

import com.ctrlcreate.commlink.model.user.User;
import com.ctrlcreate.commlink.model.user.pojo.Profile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyPageService {

    private final DBService dbService;

    public Profile getUserDetails() {
        log.debug("in function");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.debug("User: {}", user.getId());
        return user.getProfile();
    }
}
