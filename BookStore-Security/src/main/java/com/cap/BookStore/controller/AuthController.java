package com.cap.BookStore.controller;

import com.cap.BookStore.Service.AuthService;
import com.cap.BookStore.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
}
