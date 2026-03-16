package com.cap.RESULT_SERVICE.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceResponseDTO {

    private Long id;

    private Long studentId;

    private Long courseId;

    private LocalDate date;

    private String status;
}