package com.example.myreactspringbootrestapi.exception;

public class NotEnoughStockException extends DefaultException {
    public NotEnoughStockException(String message) {
        this.message = message;
    }
}
