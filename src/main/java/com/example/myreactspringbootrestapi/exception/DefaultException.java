package com.example.myreactspringbootrestapi.exception;

public class DefaultException extends Exception {
    protected String message;

    @Override
    public String getMessage() {
        return message;
    }
}
