package com.ctrlcreate.commlink.repository.project;

import com.ctrlcreate.commlink.model.project.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
}
