package com.cap.RESULT_SERVICE.controller;

import com.cap.RESULT_SERVICE.dto.ResultRequestDTO;
import com.cap.RESULT_SERVICE.dto.ResultResponseDTO;
import com.cap.RESULT_SERVICE.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @PostMapping
    public ResponseEntity<ResultResponseDTO> createResult(
            @RequestBody ResultRequestDTO request){

        return ResponseEntity.ok(resultService.createResult(request));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ResultResponseDTO>> getResultsByStudent(
            @PathVariable Long studentId){

        return ResponseEntity.ok(resultService.getResultsByStudent(studentId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ResultResponseDTO>> getResultsByCourse(
            @PathVariable Long courseId){

        return ResponseEntity.ok(resultService.getResultsByCourse(courseId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultResponseDTO> updateResult(
            @PathVariable Long id,
            @RequestBody ResultRequestDTO request){

        return ResponseEntity.ok(resultService.updateResult(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id){

        resultService.deleteResult(id);

        return ResponseEntity.noContent().build();
    }
}