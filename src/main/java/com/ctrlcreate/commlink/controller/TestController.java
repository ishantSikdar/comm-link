package com.ctrlcreate.commlink.controller;

import com.ctrlcreate.commlink.constant.APIConstants;
import com.ctrlcreate.commlink.dto.api.ApiResponse;
import com.ctrlcreate.commlink.dto.others.AccountDetails;
import com.ctrlcreate.commlink.model.user.User;
import com.ctrlcreate.commlink.util.IDUtil;
import com.ctrlcreate.commlink.util.TimeUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class TestController {

    @GetMapping("/api-fe/v1/getAcc")
    public ApiResponse<Object> getAccountInfo() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ApiResponse.builder()
                .requestId(IDUtil.generateRequestId())
                .message("")
                .status(APIConstants.STATUS_SUCCESS)
                .httpStatus(HttpStatus.OK)
                .timestamp(TimeUtil.instantToIST(Instant.now()))
                .data(
                      AccountDetails.builder()
                        .type(user.getType())
                        .email(user.getEmail())
                        .username(user.getUsername())
                        .build()
                )
                .build();
    }
}
