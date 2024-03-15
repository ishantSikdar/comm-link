package com.ctrlcreate.commlink.model.user.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class Review {
    private String id;  // user _id of reviewer
    private String feedback;
    private Date date;
}
