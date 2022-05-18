package com.accenture.gradetool.core.error;

public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException(String message) {
        super(message);
    }
}
