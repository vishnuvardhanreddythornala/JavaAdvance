package com.example.DoctorService.controller;

import com.example.DoctorService.client.UserClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DoctorController {
    private final UserClient userClient;

    public DoctorController(UserClient userClient) {
        this.userClient = userClient;
    }

    //    private final RestTemplate restTemplate;
//
//    public DoctorController(RestTemplate restTemplate){
//        this.restTemplate =  restTemplate;
//    }
    @GetMapping("/doctors")
    @CircuitBreaker(name = "userService", fallbackMethod = "userFallback")
    public String getDoctors(){
        //String users =  restTemplate.getForObject("http://UserService/users", String.class);
        String users = userClient.getUsers();
        return "Doctors Fetched. Also calling ->"+users;
    }
    public String userFallback(Throwable ex){
        return "User Service is currently unavailable. Showing cached doctor data";
    }
    @GetMapping("/doctors/config")
    public String getConfig(@Value("${doctor.service.message}") String message, @Value("${db.password}") String dbPass){
        return message +" | DB: "+ dbPass;
    }
}
