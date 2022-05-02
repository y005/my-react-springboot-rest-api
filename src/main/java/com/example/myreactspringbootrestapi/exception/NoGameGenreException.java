package com.example.myreactspringbootrestapi.exception;

public class NoGameGenreException extends DefaultException {
    public NoGameGenreException(String message) {
        this.message = message;
    }
}
