package com.ctrlcreate.commlink.service;

import com.ctrlcreate.commlink.model.project.Project;
import com.ctrlcreate.commlink.model.user.User;
import com.ctrlcreate.commlink.repository.project.ProjectRepository;
import com.ctrlcreate.commlink.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DBService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public User findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return user.get();
        }
    }

    public User saveUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            log.error("Error saving user: {}", e.getMessage());
            return null;
        }
    }

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public Project saveProject(Project project) {
        try {
            return projectRepository.save(project);
        } catch (Exception e) {
            log.error("Error saving project: {}", e.getMessage());
            return null;
        }
    }

}
