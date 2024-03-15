package com.ctrlcreate.commlink.exception;


import com.ctrlcreate.commlink.dto.api.ApiResponse;
import com.ctrlcreate.commlink.service.ErrorResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<?>> handleException(Exception ex) {
        log.error("Unexpected error occurred: {}", ex.getMessage());
        return new ResponseEntity<>(ErrorResponseService.sendBadRequestResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);

    }
}
