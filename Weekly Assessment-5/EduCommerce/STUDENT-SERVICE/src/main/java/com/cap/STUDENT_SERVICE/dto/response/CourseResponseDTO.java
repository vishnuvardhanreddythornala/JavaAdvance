package com.cap.STUDENT_SERVICE.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDTO {

    private Long id;
    private String courseName;
    private String courseCode;
    private String instructor;
    private Integer credits;
}
