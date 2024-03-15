package com.ctrlcreate.commlink.service;

import com.ctrlcreate.commlink.dto.others.Coordinates;
import com.ctrlcreate.commlink.model.project.Project;
import com.ctrlcreate.commlink.model.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class NearbyProjectsService {

    private final DBService dbService;

    public List<Project> processRecentActivityRetrieval() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Coordinates userLocation = user.getLocation();
        List<Project> recentProjects = dbService.findAllProjects();

        // Calculate distances for each project
        recentProjects.forEach(project -> {
            double distance = GeoLocation.calculateDistance(userLocation, project.getLocation());
            project.setDistanceFromUser(distance); // Assuming you have a setter for distance in Project class
        });

        // Sort projects based on distance
        recentProjects.sort(Comparator.comparingDouble(Project::getDistanceFromUser));
        return recentProjects;
    }
}
