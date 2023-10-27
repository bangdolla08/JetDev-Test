package com.example.jetdevtest.controller;

import com.example.jetdevtest.exception.NotAcceptableMediaException;
import com.example.jetdevtest.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(NotAcceptableMediaException.class)
    public final ResponseEntity<BaseResponse> handleNotAcceptableMediaException(NotAcceptableMediaException ex) {
        BaseResponse notAccepted = BaseResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(notAccepted, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<BaseResponse> handleException(Exception ex) {
        BaseResponse notAccepted = BaseResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(notAccepted, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
