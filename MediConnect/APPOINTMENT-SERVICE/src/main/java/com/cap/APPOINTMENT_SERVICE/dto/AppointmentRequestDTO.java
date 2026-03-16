package com.cap.APPOINTMENT_SERVICE.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestDTO {
    @NotBlank(message = "Name cannot be empty")
    private String patientName;

    @NotNull(message = "DoctorId cannot be null")
    @Positive(message = "DoctorId must be positive")
    private Long doctorId;

    @NotNull(message = "Date cannot be null")
    @FutureOrPresent(message = "Appointment date cannot be in the past")
    private LocalDate appointmentDate;
}
