package com.example.DoctorService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "UserService")
public interface UserClient {
    @GetMapping("/users")
    String getUsers();
}
