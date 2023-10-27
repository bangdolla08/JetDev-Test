package com.example.jetdevtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class NotAcceptableMediaException extends RuntimeException {

    private final String message;

    @Override
    public String getMessage() {
        return message;
    }

    public NotAcceptableMediaException() {
        message = null;
    }

    public NotAcceptableMediaException(String message) {
        super(message);
        this.message = message;
    }
}
