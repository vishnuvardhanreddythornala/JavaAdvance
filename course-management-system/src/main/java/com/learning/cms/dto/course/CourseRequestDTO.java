package com.learning.cms.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CourseRequestDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Positive
    private double price;

    private String duration;

    private String level;
}