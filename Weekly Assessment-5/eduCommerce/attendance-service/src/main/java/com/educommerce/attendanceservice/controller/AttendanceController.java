package com.educommerce.attendanceservice.controller;

import com.educommerce.attendanceservice.dto.AttendanceSummaryDto;
import com.educommerce.attendanceservice.entity.Attendance;
import com.educommerce.attendanceservice.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<Attendance> markAttendance(@Valid @RequestBody Attendance attendance) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(attendanceService.markAttendance(attendance));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Attendance>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByStudent(studentId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Attendance>> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByCourse(courseId));
    }

    @GetMapping("/summary")
    public ResponseEntity<AttendanceSummaryDto> getSummary(
            @RequestParam Long studentId,
            @RequestParam Long courseId) {
        return ResponseEntity.ok(attendanceService.getAttendanceSummary(studentId, courseId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable Long id,
                                                        @RequestBody Attendance attendance) {
        return ResponseEntity.ok(attendanceService.updateAttendance(id, attendance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}
