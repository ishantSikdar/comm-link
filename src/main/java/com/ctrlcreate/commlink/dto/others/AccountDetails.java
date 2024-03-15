package com.ctrlcreate.commlink.dto.others;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountDetails {

    private String username;
    private String type;
    private String email;
}
