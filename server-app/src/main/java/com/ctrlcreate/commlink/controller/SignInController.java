package com.ctrlcreate.commlink.controller;

import com.ctrlcreate.commlink.constant.APIConstants;
import com.ctrlcreate.commlink.constant.LogConstants;
import com.ctrlcreate.commlink.dto.api.ApiResponse;
import com.ctrlcreate.commlink.dto.login.LoginRequest;
import com.ctrlcreate.commlink.dto.login.LoginResponse;
import com.ctrlcreate.commlink.service.ErrorResponseService;
import com.ctrlcreate.commlink.service.LoginService;
import com.ctrlcreate.commlink.util.IDUtil;
import com.ctrlcreate.commlink.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIConstants.API_FE_ROOT_URI_1)
public class SignInController {

    private final LoginService loginService;
    private static final String ENDPOINT = APIConstants.URI_SIGNIN;

    @PostMapping(value = ENDPOINT)
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @RequestBody LoginRequest loginRequest
    ) {

        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>();
        apiResponse.setRequestId(IDUtil.generateRequestId());

        try {
            apiResponse.setData(loginService.processLogin(loginRequest));
            apiResponse.setDetail("Login Success");
            apiResponse.setStatus(APIConstants.STATUS_SUCCESS);
            apiResponse.setHttpStatus(HttpStatus.OK);
            log.info(LogConstants.LOG_ROUTE_API_STAT, apiResponse.getRequestId(), apiResponse.getStatus(), ENDPOINT);

        } catch (Exception e) {
            ErrorResponseService.setErrorResponseDTO(apiResponse, e.getMessage(), ENDPOINT, null);
            apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        apiResponse.setTimestamp(TimeUtil.instantToIST(Instant.now() ));
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }



}
