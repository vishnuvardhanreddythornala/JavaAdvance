package com.cap.RESULT_SERVICE.dto;

import lombok.Data;

@Data
public class ResultRequestDTO {

    private Long studentId;

    private Long courseId;

    private String examType;

    private Double marksObtained;

    private Double maxMarks;
}