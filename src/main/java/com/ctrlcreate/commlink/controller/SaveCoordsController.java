package com.ctrlcreate.commlink.controller;

import com.ctrlcreate.commlink.constant.APIConstants;
import com.ctrlcreate.commlink.constant.LogConstants;
import com.ctrlcreate.commlink.dto.api.ApiResponse;
import com.ctrlcreate.commlink.dto.coords.SaveCoordsRequest;
import com.ctrlcreate.commlink.service.ErrorResponseService;
import com.ctrlcreate.commlink.service.SaveCoordsService;
import com.ctrlcreate.commlink.util.IDUtil;
import com.ctrlcreate.commlink.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = APIConstants.API_FE_ROOT_URI_1)
public class SaveCoordsController {

    private final SaveCoordsService saveCoordsService;
    private static final String ENDPOINT = APIConstants.URI_SAVE_COORDS;

    @GetMapping(value = ENDPOINT)
    public ResponseEntity<ApiResponse<?>> getCoords(
            @RequestBody SaveCoordsRequest saveCoordsRequest
    ) {

        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setRequestId(IDUtil.generateRequestId());

        try {
            saveCoordsService.processSavingCoords(saveCoordsRequest);
            apiResponse.setStatus(APIConstants.STATUS_SUCCESS);
            apiResponse.setHttpStatus(HttpStatus.OK);
            apiResponse.setDetail("Successfully fetched My Page Details");
            log.info(LogConstants.LOG_ROUTE_API_STAT, apiResponse.getRequestId(), apiResponse.getStatus(), ENDPOINT);

        } catch (Exception e) {
            ErrorResponseService.setErrorResponseDTO(apiResponse, e.getMessage(), ENDPOINT, null);
            apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        }

        apiResponse.setTimestamp(TimeUtil.instantToIST(Instant.now()));
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
