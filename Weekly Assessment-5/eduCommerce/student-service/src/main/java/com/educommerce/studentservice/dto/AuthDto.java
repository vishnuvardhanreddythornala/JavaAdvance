package com.educommerce.studentservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class AuthDto {

    @Data
    public static class RegisterRequest {
        @NotBlank(message = "Name is required")
        private String name;

        @Email(message = "Email must be valid")
        @NotBlank(message = "Email is required")
        private String email;

        @NotBlank(message = "Password is required")
        private String password;

        private String department;
        private Integer semester;
    }

    @Data
    public static class LoginRequest {
        @NotBlank(message = "Email is required")
        private String email;

        @NotBlank(message = "Password is required")
        private String password;
    }

    @Data
    public static class AuthResponse {
        private String token;
        private String type = "Bearer";
        private Long studentId;
        private String email;
        private String name;

        public AuthResponse(String token, Long studentId, String email, String name) {
            this.token = token;
            this.studentId = studentId;
            this.email = email;
            this.name = name;
        }
    }
}
