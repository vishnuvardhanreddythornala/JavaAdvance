package com.cap.STUDENT_SERVICE.controller;

import com.cap.STUDENT_SERVICE.dto.request.EnrollmentRequestDTO;
import com.cap.STUDENT_SERVICE.dto.response.CourseResponseDTO;
import com.cap.STUDENT_SERVICE.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/enroll")
    public String enrollStudent(
            @RequestBody EnrollmentRequestDTO request
    ){
        return enrollmentService.enrollStudent(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    @GetMapping("/students/{id}/courses")
    public List<CourseResponseDTO> getCoursesByStudent(
            @PathVariable Long id
    ){
        return enrollmentService.getCoursesByStudentId(id);
    }
}