package com.cap.STUDENT_SERVICE.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RegisterResponseDTO {

    private String message;
    private String email;
}