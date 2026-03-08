package com.learning.cms.dto.auth;

import com.learning.cms.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDTO {

    @NotBlank
    private String fullName;

    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    private Role role;
}