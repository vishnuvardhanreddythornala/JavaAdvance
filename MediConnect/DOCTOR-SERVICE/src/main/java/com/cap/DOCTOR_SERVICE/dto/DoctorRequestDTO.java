package com.cap.DOCTOR_SERVICE.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDTO {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Specialization required")
    private String specialization;

    @Min(value = 0, message = "Experience must be positive")
    private int experience;

    @NotBlank(message = "Hospital name required")
    private String hospitalName;

    @NotBlank(message = "Availability required")
    private String availability;
}