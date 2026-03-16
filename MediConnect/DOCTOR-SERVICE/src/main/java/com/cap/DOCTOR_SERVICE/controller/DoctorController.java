package com.cap.DOCTOR_SERVICE.controller;

import com.cap.DOCTOR_SERVICE.dto.DoctorRequestDTO;
import com.cap.DOCTOR_SERVICE.dto.DoctorResponseDTO;
import com.cap.DOCTOR_SERVICE.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable Long doctorId) {
        return ResponseEntity.ok(doctorService.getDoctor(doctorId));
    }

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> saveDoctor(@Valid @RequestBody DoctorRequestDTO doctorRequestDTO) {
        return ResponseEntity.ok(doctorService.createDoctor(doctorRequestDTO));
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorResponseDTO> modifyDoctor(@PathVariable Long doctorId,@Valid @RequestBody DoctorRequestDTO doctorRequestDTO) {
        return ResponseEntity.ok(doctorService.updateDoctor(doctorId, doctorRequestDTO));
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<DoctorResponseDTO> removeDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(doctorService.deleteDoctor(doctorId));
    }
}
