package com.cap.ATTENDANCE_SERVICE.dto;

import lombok.Data;

@Data
public class AttendanceRequestDTO {

    private Long studentId;

    private Long courseId;

    private String status;
}