package com.learning.cms.dto.course;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseResponseDTO {

    private Long id;

    private String title;

    private String description;

    private double price;

    private String duration;

    private String level;

    private String instructorName;

    private LocalDateTime createdAt;
}