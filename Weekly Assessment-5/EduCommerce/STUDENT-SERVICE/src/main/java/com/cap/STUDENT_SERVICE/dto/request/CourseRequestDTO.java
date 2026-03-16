package com.cap.STUDENT_SERVICE.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseRequestDTO {
    @NotBlank
    private String courseName;

    @NotBlank
    private String courseCode;

    @NotBlank
    private String instructor;

    @NotNull
    @Min(1)
    @Max(4)
    private Integer credits;
}
