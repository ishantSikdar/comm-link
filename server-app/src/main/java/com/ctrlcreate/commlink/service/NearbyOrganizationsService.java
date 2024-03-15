package com.ctrlcreate.commlink.service;

import com.ctrlcreate.commlink.dto.others.Coordinates;
import com.ctrlcreate.commlink.model.project.Project;
import com.ctrlcreate.commlink.model.user.User;
import com.ctrlcreate.commlink.model.user.pojo.Profile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NearbyOrganizationsService {

    private final DBService dbService;

    public List<Profile> processNearbyOrganisationRetrieval() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Coordinates userLocation = user.getLocation();
        List<User> nearbyOrganizations = dbService.findAllOrganisations();

        // Calculate distances for each project
        nearbyOrganizations.forEach(organization -> {
            double distance = GeoLocation.calculateDistance(userLocation, organization.getLocation());
            organization.setDistanceFromUser(distance); // Assuming you have a setter for distance in Project class
        });

        // Sort projects based on distance
        nearbyOrganizations.sort(Comparator.comparingDouble(User::getDistanceFromUser));
        nearbyOrganizations.stream()
                .map(user ->    )
        return nearbyOrganizations;
    }
}
