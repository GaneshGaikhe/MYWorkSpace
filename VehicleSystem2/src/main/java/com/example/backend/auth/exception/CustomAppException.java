package com.example.backend.auth.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom exception for application-specific errors.
 * Can be caught by GlobalExceptionHandler.
 */
public class CustomAppException extends RuntimeException {

    private final HttpStatus status;

    public CustomAppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
