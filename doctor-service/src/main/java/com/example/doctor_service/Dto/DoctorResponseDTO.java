package com.example.doctor_service.Dto;

import lombok.Data;

@Data
public class DoctorResponseDTO {
    private Long doctorId;
    private String name;
    private String Specialization;
    private Integer experience;
    private String hospitalName;
    private String availability;


}
