package com.ctrlcreate.commlink.dto.api;

import com.ctrlcreate.commlink.util.DateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonPropertyOrder({ "requestId", "status", "message", "detail", "timestamp", "data" })
public class ApiResponse<T> {

    @JsonProperty("requestId")
    private String requestId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("detail")
    private String detail;

    @JsonProperty("timestamp")
    @JsonSerialize(using = DateSerializer.class)
    private Date timestamp;

    @JsonProperty("data")
    private T data;

    @JsonIgnore
    private HttpStatus httpStatus;

}