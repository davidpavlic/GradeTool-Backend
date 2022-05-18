package com.accenture.gradetool.core.error;

public class ExceptionResponse {

    private final Exception exception;

    public ExceptionResponse(Exception exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return exception.getMessage();
    }
}
