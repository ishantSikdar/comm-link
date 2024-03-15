package com.ctrlcreate.commlink.service;

import com.ctrlcreate.commlink.dto.coords.SaveCoordsRequest;
import com.ctrlcreate.commlink.dto.others.Coordinates;
import com.ctrlcreate.commlink.model.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaveCoordsService {

    private final DBService dbService;
    public void processSavingCoords(SaveCoordsRequest saveCoordsRequest) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Coordinates coordinates = Coordinates.builder()
                .latitude(saveCoordsRequest.getLatitude())
                .longitude(saveCoordsRequest.getLongitude())
                .build();

        user.setLocation(coordinates);
        User savedUser = dbService.saveUser(user);
        log.info("Saved Coords User: {}", savedUser.getId());
    }
}
