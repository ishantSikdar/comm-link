package com.ctrlcreate.commlink.model.posts;

import com.ctrlcreate.commlink.constant.DBConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = DBConstants.POSTS)
public class Post {

    @Id
    private String id;
    private String postOf;  // userId
    private String text;
    private byte[] image;

}
