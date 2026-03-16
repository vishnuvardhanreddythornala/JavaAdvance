package com.cap.ATTENDANCE_SERVICE.exceptions;

import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<?> handleStudentNotFound(StudentNotFoundException ex){

        Map<String,Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("error", ex.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<?> handleFeignNotFound(FeignException.NotFound ex){

        Map<String,Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("error", "Student not found");

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleFeignException(FeignException ex){

        Map<String,Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("error", "Student service unavailable");

        return ResponseEntity.status(503).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex){

        Map<String,Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("error", ex.getMessage());

        return ResponseEntity.badRequest().body(error);
    }
}