package com.ctrlcreate.commlink.service;

import com.ctrlcreate.commlink.dto.project.NewProjectRequest;
import com.ctrlcreate.commlink.model.project.Project;
import com.ctrlcreate.commlink.model.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewProjectService {

    private final DBService dbService;

    public void processNewProjectService(
            NewProjectRequest newProjectRequest
    ) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Project project = dbService.saveProject(
                Project.builder()
                        .userId(user.getId())
                        .name(newProjectRequest.getName())
                        .location(newProjectRequest.getLocation())
                        .eventDate(newProjectRequest.getEventDate())
                        .createdAt(Instant.now())
                        .build()
        );
        log.info("Project saved : {}", project.getId());
    }

}
