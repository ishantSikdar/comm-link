    package com.ctrlcreate.commlink.controller;

    import com.ctrlcreate.commlink.constant.APIConstants;
    import com.ctrlcreate.commlink.constant.LogConstants;
    import com.ctrlcreate.commlink.dto.api.ApiResponse;
    import com.ctrlcreate.commlink.service.ErrorResponseService;
    import com.ctrlcreate.commlink.service.NearbyProjectsService;
    import com.ctrlcreate.commlink.util.IDUtil;
    import com.ctrlcreate.commlink.util.TimeUtil;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import java.time.Instant;
    import java.util.List;

    @RestController
    @Slf4j
    @RequiredArgsConstructor
    @RequestMapping(path = APIConstants.API_FE_ROOT_URI_1)
    public class RecentProjectsController {

        private final NearbyProjectsService nearbyProjectsService;
        private static final String ENDPOINT = APIConstants.URI_PROJECTS;
        @GetMapping(value = ENDPOINT)
        public ResponseEntity<ApiResponse<List<?>>> getRecentActivity() {
            ApiResponse<List<?>> apiResponse = new ApiResponse<>();
            apiResponse.setRequestId(IDUtil.generateRequestId());

            try {
                apiResponse.setData(nearbyProjectsService.processRecentActivityRetrieval());
                apiResponse.setDetail("Fetched Nearby Projects");
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
