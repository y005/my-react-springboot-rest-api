package com.example.myreactspringbootrestapi.controller;

import com.example.myreactspringbootrestapi.exception.InvalidOrderParameterException;
import com.example.myreactspringbootrestapi.exception.InvalidProductParameterException;
import com.example.myreactspringbootrestapi.exception.NoGameGenreException;
import com.example.myreactspringbootrestapi.exception.NoOrderStatusException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity getException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Input valid id");
    }

    @ExceptionHandler({InvalidOrderParameterException.class, InvalidProductParameterException.class, NoGameGenreException.class, NoOrderStatusException.class})
    public ResponseEntity handleParamErrorException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity handleValidException(MethodArgumentNotValidException exception) {
        String errorMessage = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorMessage);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity handleValidException1(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("You have purchased more than stock");
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity handleValidException2(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Input correct type of infomation");
    }
}
