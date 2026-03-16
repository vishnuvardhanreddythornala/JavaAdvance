package com.cap.APPOINTMENT_SERVICE.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Entity
@Table(name = "Appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    private String patientName;
    private Long doctorId;
    private LocalDate appointmentDate;
    private String status;

}
