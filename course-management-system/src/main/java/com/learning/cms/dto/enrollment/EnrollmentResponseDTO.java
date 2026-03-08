package com.learning.cms.dto.enrollment;

import com.learning.cms.entity.EnrollmentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EnrollmentResponseDTO {

    private Long id;

    private String courseTitle;

    private String studentName;

    private EnrollmentStatus status;

    private Double progressPercentage;

    private LocalDateTime enrollmentDate;
}