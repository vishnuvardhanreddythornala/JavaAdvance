package com.cap.RESULT_SERVICE.client;

import com.cap.RESULT_SERVICE.dto.AttendanceResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "attendance-service")
public interface AttendanceClient {

    @GetMapping("/attendance/student/{studentId}")
    List<AttendanceResponseDTO> getAttendanceByStudent(
            @PathVariable Long studentId
    );

}