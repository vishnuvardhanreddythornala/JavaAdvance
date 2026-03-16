package com.cap.APPOINTMENT_SERVICE.controller;

import com.cap.APPOINTMENT_SERVICE.dto.AppointmentRequestDTO;
import com.cap.APPOINTMENT_SERVICE.dto.AppointmentResponseDTO;
import com.cap.APPOINTMENT_SERVICE.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private  final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentRequestDTO request) {

        return ResponseEntity.ok(appointmentService.createAppointment(request));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments() {
        return  ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> deleteAppointmentById(@PathVariable Long id) {
        return  ResponseEntity.ok(appointmentService.deleteAppointment(id));
    }
}

