package com.educommerce.attendanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttendanceSummaryDto {
    private Long studentId;
    private Long courseId;
    private long totalClasses;
    private long presentCount;
    private long absentCount;
    private double attendancePercentage;
}
