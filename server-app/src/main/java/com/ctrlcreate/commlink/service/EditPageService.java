package com.ctrlcreate.commlink.service;

import com.ctrlcreate.commlink.dto.mypage.EditPageRequest;
import com.ctrlcreate.commlink.model.user.User;
import com.ctrlcreate.commlink.model.user.pojo.Profile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class EditPageService {

    public final DBService dbService;

    public void processEditPage(
            EditPageRequest editPageRequest
    ) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Profile userProfile = user.getProfile();

        userProfile.setName(!Objects.equals(editPageRequest.getName(), "") ? editPageRequest.getName() : userProfile.getName());
        userProfile.setHeadline(!Objects.equals(editPageRequest.getHeadline(), "") ? editPageRequest.getHeadline() : userProfile.getHeadline());
        userProfile.setAbout(!Objects.equals(editPageRequest.getAbout(), "") ? editPageRequest.getAbout() : userProfile.getAbout());
        userProfile.setRating(!Objects.equals(editPageRequest.getRating(), -1.0) ? editPageRequest.getRating() : userProfile.getRating());
        userProfile.setPhoto(!Objects.equals(editPageRequest.getPhoto(), null) ? editPageRequest.getPhoto() : userProfile.getPhoto());
        userProfile.setBanner(!Objects.equals(editPageRequest.getBanner(), null) ? editPageRequest.getBanner() : userProfile.getBanner());

        user.setProfile(userProfile);
        User savedUser = dbService.saveUser(user);
        log.info("Updated My Page: {}", savedUser.getId());
    }

}
