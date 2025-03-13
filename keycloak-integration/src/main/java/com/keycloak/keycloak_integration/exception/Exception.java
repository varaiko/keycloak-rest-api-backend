package com.keycloak.keycloak_integration.exception;

import org.springframework.http.HttpStatus;

public class Exception extends Throwable {

    private HttpStatus status;
    private String message;

    public Exception(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
