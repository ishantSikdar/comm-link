package com.ctrlcreate.commlink.model.project;

import com.ctrlcreate.commlink.constant.DBConstants;
import com.ctrlcreate.commlink.dto.others.Coordinates;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Builder
@Document(collection = DBConstants.PROJECTS)
public class Project {

    @Id
    private String id;
    private String userId;
    private String name;
    private String description;
    private Coordinates location;
    private Date eventDate;
    private Instant createdAt;
    private Instant updatedAt;

    @JsonIgnore
    @Transient
    private Double distanceFromUser;
}
