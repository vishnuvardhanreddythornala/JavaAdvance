package com.educommerce.resultservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "results")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Student ID is required")
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @NotNull(message = "Course ID is required")
    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @NotBlank(message = "Exam type is required")
    @Column(name = "exam_type", nullable = false)
    private String examType;

    @NotNull(message = "Marks obtained are required")
    @Column(name = "marks_obtained", nullable = false)
    private Double marksObtained;

    @NotNull(message = "Max marks are required")
    @Column(name = "max_marks", nullable = false)
    private Double maxMarks;

    @Column
    private String grade;

    @PrePersist
    @PreUpdate
    protected void calculateGrade() {
        if (marksObtained != null && maxMarks != null && maxMarks > 0) {
            double percentage = (marksObtained / maxMarks) * 100;
            if (percentage >= 90) grade = "A+";
            else if (percentage >= 80) grade = "A";
            else if (percentage >= 70) grade = "B";
            else if (percentage >= 60) grade = "C";
            else if (percentage >= 50) grade = "D";
            else grade = "F";
        }
    }
}
