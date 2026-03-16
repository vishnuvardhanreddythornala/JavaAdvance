package com.cap.STUDENT_SERVICE.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EnrollmentResponseDTO {

    private Long enrollmentId;
    private Long studentId;
    private Long courseId;
    private LocalDate enrollmentDate;
}
