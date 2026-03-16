package com.cap.STUDENT_SERVICE.dto.response;

import com.cap.STUDENT_SERVICE.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {

    @NotBlank
    private String token;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private Role role;
}
