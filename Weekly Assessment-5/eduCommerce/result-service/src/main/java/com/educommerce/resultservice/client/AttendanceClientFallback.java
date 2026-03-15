package com.educommerce.resultservice.client;

import com.educommerce.resultservice.dto.AttendanceSummaryDto;
import org.springframework.stereotype.Component;

@Component
public class AttendanceClientFallback implements AttendanceClient {

    @Override
    public AttendanceSummaryDto getAttendanceSummary(Long studentId, Long courseId) {
        return new AttendanceSummaryDto(studentId, courseId, 0, 0, 0, 0.0);
    }
}
