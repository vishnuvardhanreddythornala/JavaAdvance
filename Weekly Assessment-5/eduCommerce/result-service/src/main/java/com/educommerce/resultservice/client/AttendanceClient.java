package com.educommerce.resultservice.client;

import com.educommerce.resultservice.dto.AttendanceSummaryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "attendance-service", fallback = AttendanceClientFallback.class)
public interface AttendanceClient {

    @GetMapping("/attendance/summary")
    AttendanceSummaryDto getAttendanceSummary(@RequestParam Long studentId,
                                               @RequestParam Long courseId);
}
