package com.example.jetdevtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private final String message;

    @Override
    public String getMessage() {
        return message;
    }

    public NotFoundException() {
        message = null;
    }

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
