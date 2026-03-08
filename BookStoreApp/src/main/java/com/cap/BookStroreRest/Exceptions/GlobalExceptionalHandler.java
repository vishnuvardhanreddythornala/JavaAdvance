package com.cap.BookStroreRest.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime (RuntimeException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
