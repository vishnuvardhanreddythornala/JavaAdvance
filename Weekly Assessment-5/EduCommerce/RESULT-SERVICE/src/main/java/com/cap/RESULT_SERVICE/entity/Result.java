package com.cap.RESULT_SERVICE.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    private Long courseId;

    private String examType;   // MIDTERM, FINAL, QUIZ

    private Double marksObtained;

    private Double maxMarks;

    private String grade;
}