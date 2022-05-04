package com.example.myreactspringbootrestapi.exception;

public class DefaultException extends RuntimeException {
    protected String message;

    @Override
    public String getMessage() {
        return message;
    }
}
