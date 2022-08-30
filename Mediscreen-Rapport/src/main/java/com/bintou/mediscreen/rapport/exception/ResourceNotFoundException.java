package com.bintou.mediscreen.rapport.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Exception if rapport not found
     * @param message the error message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}