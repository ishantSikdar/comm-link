package com.ctrlcreate.commlink.dto.others;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Coordinates {
    private String name;
    private Double longitude;
    private Double latitude;
}
