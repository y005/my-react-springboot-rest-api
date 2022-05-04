package com.example.myreactspringbootrestapi.exception;

public class NoOrderStatusException extends DefaultException {
    public NoOrderStatusException(String message) {
        this.message = message;
    }
}
