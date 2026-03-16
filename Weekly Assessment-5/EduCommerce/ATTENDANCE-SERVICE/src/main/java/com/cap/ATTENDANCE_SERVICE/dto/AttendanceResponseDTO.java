package com.cap.ATTENDANCE_SERVICE.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AttendanceResponseDTO {

    private Long id;
    private Long studentId;
    private Long courseId;
    private LocalDate date;
    private String status;
}