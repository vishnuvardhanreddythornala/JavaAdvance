package com.cap.STUDENT_SERVICE.controller;

import com.cap.STUDENT_SERVICE.dto.request.CourseRequestDTO;
import com.cap.STUDENT_SERVICE.dto.response.CourseResponseDTO;
import com.cap.STUDENT_SERVICE.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public CourseResponseDTO createCourse(
            @RequestBody CourseRequestDTO request
    ){
        return courseService.createCourse(request);
    }

    @GetMapping
    public List<CourseResponseDTO> getAllCourses(){
        return courseService.getAllCourses();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public CourseResponseDTO updateCourse(
            @PathVariable Long id,
            @RequestBody CourseRequestDTO request
    ){
        return courseService.updateCourse(id, request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
    }
}