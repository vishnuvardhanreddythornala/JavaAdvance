package com.cap.RESULT_SERVICE.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultResponseDTO {

    private Long id;

    private Long studentId;

    private Long courseId;

    private String examType;

    private Double marksObtained;

    private Double maxMarks;

    private String grade;
}