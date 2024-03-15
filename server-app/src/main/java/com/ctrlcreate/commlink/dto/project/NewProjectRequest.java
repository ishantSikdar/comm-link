package com.ctrlcreate.commlink.dto.project;

import com.ctrlcreate.commlink.dto.others.Coordinates;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class NewProjectRequest {
    private String name;
    private String description;
    private Coordinates location;
    private Date eventDate;
}
