package com.cap.STUDENT_SERVICE.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrollmentRequestDTO {
    @NotNull
    private Long studentId;
    @NotNull
    private Long courseId;
}