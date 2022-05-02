package com.example.myreactspringbootrestapi.exception;

public class InvalidProductParameterException extends DefaultException{
    public InvalidProductParameterException(String message) {
        this.message = message;
    }
}
