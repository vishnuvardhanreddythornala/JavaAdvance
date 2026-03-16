package com.cap.STUDENT_SERVICE.controller;

import com.cap.STUDENT_SERVICE.dto.response.StudentResponseDTO;
import com.cap.STUDENT_SERVICE.entity.Student;
import com.cap.STUDENT_SERVICE.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<StudentResponseDTO> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    @GetMapping("/{id}")
    public StudentResponseDTO getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    @PutMapping("/{id}")
    public StudentResponseDTO updateStudent(
            @PathVariable Long id,
            @RequestBody Student student
    ){
        return studentService.updateStudent(id, student);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    @DeleteMapping("/{id}")
    public StudentResponseDTO deleteStudent(
            @PathVariable Long id
    ){
        return studentService.deleteStudent(id);
    }
}