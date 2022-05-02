package com.example.myreactspringbootrestapi.exception;

public class InvalidOrderParameterException extends DefaultException{
    public InvalidOrderParameterException(String message) {
        this.message = message;
    }
}
