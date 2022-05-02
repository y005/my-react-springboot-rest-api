package com.example.myreactspringbootrestapi.exception;

public class NoGameGenreException extends Exception {
    private String msg;

    public NoGameGenreException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
