package com.cap.STUDENT_SERVICE.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String department;
    private Integer semester;
}
