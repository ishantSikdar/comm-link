package com.ctrlcreate.commlink.dto.register;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class    RegisterRequest {

    private String name;
    private String username;
    private String email;
    private String password;
    private String type;

}
