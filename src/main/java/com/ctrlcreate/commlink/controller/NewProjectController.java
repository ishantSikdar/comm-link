package com.ctrlcreate.commlink.controller;

import com.ctrlcreate.commlink.constant.APIConstants;
import com.ctrlcreate.commlink.constant.LogConstants;
import com.ctrlcreate.commlink.dto.api.ApiResponse;
import com.ctrlcreate.commlink.dto.login.LoginRequest;
import com.ctrlcreate.commlink.dto.login.LoginResponse;
import com.ctrlcreate.commlink.dto.project.NewProjectRequest;
import com.ctrlcreate.commlink.service.ErrorResponseService;
import com.ctrlcreate.commlink.service.NewProjectService;
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

@RestController
@Slf4j
@RequestMapping(path = APIConstants.API_FE_ROOT_URI_1)
@RequiredArgsConstructor
public class NewProjectController {

    private final NewProjectService newProjectService;

    private static final String ENDPOINT = APIConstants.URI_PROJECTS;

    @PostMapping(value = ENDPOINT)
    public ResponseEntity<ApiResponse<?>> login(
            @RequestBody NewProjectRequest newProjectRequest
            ) {

        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setRequestId(IDUtil.generateRequestId());

        try {
            newProjectService.processNewProjectService(newProjectRequest);
            apiResponse.setMessage("Added new project");
            apiResponse.setStatus(APIConstants.STATUS_SUCCESS);
            apiResponse.setHttpStatus(HttpStatus.OK);
            log.info(LogConstants.LOG_ROUTE_API_STAT, apiResponse.getRequestId(), apiResponse.getStatus(), ENDPOINT);

        } catch (Exception e) {
            ErrorResponseService.setErrorResponseDTO(apiResponse, e.getMessage(), ENDPOINT, null);
            apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        apiResponse.setTimestamp(TimeUtil.instantToIST(Instant.now()));
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

}
