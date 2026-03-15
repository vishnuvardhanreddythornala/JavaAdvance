package com.educommerce.resultservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceSummaryDto {
    private Long studentId;
    private Long courseId;
    private long totalClasses;
    private long presentCount;
    private long absentCount;
    private double attendancePercentage;
}
