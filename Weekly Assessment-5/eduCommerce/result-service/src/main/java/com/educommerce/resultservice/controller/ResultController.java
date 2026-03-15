package com.educommerce.resultservice.controller;

import com.educommerce.resultservice.dto.StudentReportDto;
import com.educommerce.resultservice.entity.Result;
import com.educommerce.resultservice.service.ResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @PostMapping
    public ResponseEntity<Result> createResult(@Valid @RequestBody Result result) {
        return ResponseEntity.status(HttpStatus.CREATED).body(resultService.createResult(result));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Result>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(resultService.getResultsByStudent(studentId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Result>> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(resultService.getResultsByCourse(courseId));
    }

    @GetMapping("/student/{studentId}/gpa")
    public ResponseEntity<Map<String, Double>> getGpa(@PathVariable Long studentId) {
        return ResponseEntity.ok(Map.of("gpa", resultService.calculateGpa(studentId)));
    }

    @GetMapping("/student/{studentId}/report")
    public ResponseEntity<StudentReportDto> getStudentReport(@PathVariable Long studentId) {
        return ResponseEntity.ok(resultService.getStudentReport(studentId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable Long id,
                                          @RequestBody Result result) {
        return ResponseEntity.ok(resultService.updateResult(id, result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
        resultService.deleteResult(id);
        return ResponseEntity.noContent().build();
    }
}
