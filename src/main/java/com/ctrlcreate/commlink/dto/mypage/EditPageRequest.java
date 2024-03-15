package com.ctrlcreate.commlink.dto.mypage;

import com.ctrlcreate.commlink.model.user.pojo.Review;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditPageRequest {

    private String name;
    private byte[] photo;
    private byte[] banner;
    private String headline;
    private Double rating;
    private String about;

}
