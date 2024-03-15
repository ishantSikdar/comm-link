package com.ctrlcreate.commlink.service;

import com.ctrlcreate.commlink.constant.APIConstants;
import com.ctrlcreate.commlink.constant.AppConstant;
import com.ctrlcreate.commlink.constant.LogConstants;
import com.ctrlcreate.commlink.dto.api.ApiResponse;
import com.ctrlcreate.commlink.util.IDUtil;
import com.ctrlcreate.commlink.util.TimeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;

@Slf4j
public class ErrorResponseService {
    static ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .setDateFormat(new SimpleDateFormat(AppConstant.DATEFORMAT_MMDDYYYY_HHMMSS_AM_PM));


    public static void sendInvalidTokenFailedResponse(@NonNull HttpServletResponse response) throws IOException {
        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setRequestId(IDUtil.generateRequestId());
        apiResponse.setStatus(APIConstants.STATUS_FAILED);
        apiResponse.setMessage(AppConstant.SESSION_EXPIRED_MSG);
        apiResponse.setDetail(AppConstant.SESSION_EXPIRED_DETAIL);
        apiResponse.setTimestamp(TimeUtil.instantToIST(Instant.now()));
        apiResponse.setData(null);

        String jsonResponse = objectMapper.writeValueAsString(apiResponse);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(AppConstant.APPLICATION_JSON);
        response.getWriter().write(jsonResponse);
    }

    public static void sendMissingAuthResponse(@NonNull HttpServletResponse response) throws IOException {
        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setRequestId(IDUtil.generateRequestId());
        apiResponse.setStatus(APIConstants.STATUS_FAILED);
        apiResponse.setMessage(AppConstant.NO_AUTH_PRESENT_MSG);
        apiResponse.setDetail(AppConstant.NO_AUTH_PRESENT_DETAIL);
        apiResponse.setTimestamp(TimeUtil.instantToIST(Instant.now()));
        apiResponse.setData(null);

        String jsonResponse = objectMapper.writeValueAsString(apiResponse);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(AppConstant.APPLICATION_JSON);
        response.getWriter().write(jsonResponse);
    }

    public static void sendNoAuthHeaderResponse(HttpServletResponse response) throws IOException {

        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setRequestId(IDUtil.generateRequestId());
        apiResponse.setStatus(APIConstants.STATUS_FAILED);
        apiResponse.setMessage("Send a valid AuthToken");
        apiResponse.setDetail("No AuthToken present or Invalid Auth Token");
        apiResponse.setTimestamp(TimeUtil.instantToIST(Instant.now()));
        apiResponse.setData(null);

        String jsonResponse = objectMapper.writeValueAsString(apiResponse);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(AppConstant.APPLICATION_JSON);
        response.getWriter().write(jsonResponse);
    }



    public static ApiResponse<?> sendBadRequestResponse(String error) {

        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setRequestId(IDUtil.generateRequestId());
        apiResponse.setStatus(APIConstants.STATUS_FAILED);
        apiResponse.setMessage("");
        apiResponse.setDetail(error);
        apiResponse.setTimestamp(TimeUtil.instantToIST(Instant.now()));
        apiResponse.setData(null);

        return apiResponse;
    }

    public static void sendUnknownEndpoint(
            @NonNull HttpServletResponse response

    ) throws IOException {
        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setRequestId(IDUtil.generateRequestId());
        apiResponse.setStatus(APIConstants.STATUS_FAILED);
        apiResponse.setMessage("");
        apiResponse.setDetail("Invalid endpoint access attempt");
        apiResponse.setTimestamp(TimeUtil.instantToIST(Instant.now()));
        apiResponse.setData(null);

        String jsonResponse = objectMapper.writeValueAsString(apiResponse);
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.setContentType(AppConstant.APPLICATION_JSON);
        response.getWriter().write(jsonResponse);
    }


    public static void sendInvalidRequestResponse(HttpServletResponse response, String cause) throws IOException {
        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setRequestId(IDUtil.generateRequestId());
        apiResponse.setStatus(APIConstants.STATUS_FAILED);
        apiResponse.setMessage("");
        apiResponse.setDetail(cause);
        apiResponse.setData(null);

        String jsonResponse = objectMapper.writeValueAsString(apiResponse);
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType(AppConstant.APPLICATION_JSON);
        response.getWriter().write(jsonResponse);
    }

    public static <T> void setErrorResponseDTO(ApiResponse<T> apiResponse, String message, String URI, T data) {
        apiResponse.setStatus(APIConstants.STATUS_FAILED);
        apiResponse.setData(data);
        apiResponse.setDetail(message);

        log.error(LogConstants.LOG_ROUTE_API_ERR, apiResponse.getRequestId(), apiResponse.getMessage(), URI);
    }
}
