package com.learning.cms.controller;

import com.learning.cms.dto.enrollment.EnrollmentRequestDTO;
import com.learning.cms.dto.enrollment.EnrollmentResponseDTO;
import com.learning.cms.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
@Tag(name = "Enrollment", description = "API's related to enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    @Operation(summary = "Enroll a student in a course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student enrolled successfully"),
            @ApiResponse(responseCode = "404", description = "Course or student not found"),
            @ApiResponse(responseCode = "400", description = "Invalid enrollment request")
    })
    public ResponseEntity<EnrollmentResponseDTO> enrollStudent(
            @Valid @RequestBody EnrollmentRequestDTO dto
    ) {

        EnrollmentResponseDTO response = enrollmentService.enrollStudent(dto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get all enrollments for a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enrollments retrieved successfully")
    })
    public ResponseEntity<List<EnrollmentResponseDTO>> getStudentEnrollments(
            @PathVariable Long studentId
    ) {

        List<EnrollmentResponseDTO> enrollments =
                enrollmentService.getStudentEnrollments(studentId);

        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Get all students enrolled in a course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enrollments retrieved successfully")
    })
    public ResponseEntity<List<EnrollmentResponseDTO>> getCourseEnrollments(
            @PathVariable Long courseId
    ) {

        List<EnrollmentResponseDTO> enrollments =
                enrollmentService.getCourseEnrollments(courseId);

        return ResponseEntity.ok(enrollments);
    }
}