package com.learning.cms.dto.enrollment;

import lombok.Data;

@Data
public class EnrollmentRequestDTO {

    private Long courseId;

    private Long studentId;
}