package com.educommerce.studentservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course name is required")
    @Column(nullable = false)
    private String courseName;

    @NotBlank(message = "Course code is required")
    @Column(nullable = false, unique = true)
    private String courseCode;

    private String instructor;

    private Integer credits;
}
