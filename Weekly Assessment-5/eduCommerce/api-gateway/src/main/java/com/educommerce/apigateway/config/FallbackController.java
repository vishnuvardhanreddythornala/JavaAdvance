package com.educommerce.apigateway.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/student")
    public ResponseEntity<Map<String, String>> studentFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("message", "Student Service is currently unavailable. Please try again later."));
    }

    @GetMapping("/attendance")
    public ResponseEntity<Map<String, String>> attendanceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("message", "Attendance Service is currently unavailable. Please try again later."));
    }

    @GetMapping("/result")
    public ResponseEntity<Map<String, String>> resultFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("message", "Result Service is currently unavailable. Please try again later."));
    }
}
