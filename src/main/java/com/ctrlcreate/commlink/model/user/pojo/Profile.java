package com.ctrlcreate.commlink.model.user.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Profile {

    private String name;
    private byte[] photo;
    private byte[] banner;
    private String headline;
    private Double rating;
    private String about;
    private Review[] reviews;
    private String[] projects;

}
