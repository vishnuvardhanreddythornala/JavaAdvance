package com.educommerce.studentservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private String email;
    private String department;
    private Integer semester;
    private LocalDateTime createdAt;
}
