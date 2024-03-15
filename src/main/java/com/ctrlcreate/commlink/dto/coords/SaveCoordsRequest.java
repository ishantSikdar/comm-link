package com.ctrlcreate.commlink.dto.coords;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SaveCoordsRequest {

    private Double latitude;
    private Double longitude;
}
