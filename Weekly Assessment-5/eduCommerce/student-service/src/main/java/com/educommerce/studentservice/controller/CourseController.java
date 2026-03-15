package com.educommerce.studentservice.controller;

import com.educommerce.studentservice.entity.Course;
import com.educommerce.studentservice.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createCourse(course));
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(studentService.getAllCourses());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id,
                                                @RequestBody Course course) {
        return ResponseEntity.ok(studentService.updateCourse(id, course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        studentService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
