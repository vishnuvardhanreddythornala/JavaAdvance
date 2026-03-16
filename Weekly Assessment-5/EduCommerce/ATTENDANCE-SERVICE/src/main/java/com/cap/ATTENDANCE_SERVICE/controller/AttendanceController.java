package com.cap.ATTENDANCE_SERVICE.controller;

import com.cap.ATTENDANCE_SERVICE.dto.AttendanceRequestDTO;
import com.cap.ATTENDANCE_SERVICE.dto.AttendanceResponseDTO;
import com.cap.ATTENDANCE_SERVICE.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<AttendanceResponseDTO> markAttendance(
            @RequestBody AttendanceRequestDTO request){

        AttendanceResponseDTO response =
                attendanceService.markAttendance(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceResponseDTO>> getAttendanceByStudent(
            @PathVariable Long studentId){

        return ResponseEntity.ok(
                attendanceService.getAttendanceByStudent(studentId)
        );
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<AttendanceResponseDTO>> getAttendanceByCourse(
            @PathVariable Long courseId){

        return ResponseEntity.ok(
                attendanceService.getAttendanceByCourse(courseId)
        );
    }
}