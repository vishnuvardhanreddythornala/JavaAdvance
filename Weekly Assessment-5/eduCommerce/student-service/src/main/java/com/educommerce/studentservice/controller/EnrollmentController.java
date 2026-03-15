package com.educommerce.studentservice.controller;

import com.educommerce.studentservice.entity.Course;
import com.educommerce.studentservice.entity.Enrollment;
import com.educommerce.studentservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EnrollmentController {

    private final StudentService studentService;

    @PostMapping("/enroll")
    public ResponseEntity<Enrollment> enrollStudent(@RequestBody Map<String, Long> body) {
        Long studentId = body.get("studentId");
        Long courseId = body.get("courseId");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.enrollStudent(studentId, courseId));
    }

    @GetMapping("/students/{id}/courses")
    public ResponseEntity<List<Course>> getStudentCourses(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentCourses(id));
    }
}
