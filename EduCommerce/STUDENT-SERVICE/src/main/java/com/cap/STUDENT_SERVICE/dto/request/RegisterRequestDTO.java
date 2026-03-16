package com.cap.STUDENT_SERVICE.dto.request;

import com.cap.STUDENT_SERVICE.entity.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    private String department;

    @Min(1)
    @Max(8)
    private Integer semester;

    @NotNull
    private Role role;
}