package com.example.UserService.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class    UserController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/users")
    public String getUsers(){
        return "Users fetched from USER SERVICE running on port: "+port;
    }
}
