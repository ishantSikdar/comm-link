package com.ctrlcreate.commlink.repository.project;

import com.ctrlcreate.commlink.model.project.Project;
import com.ctrlcreate.commlink.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

}
