package com.learning.cms.service;

import com.learning.cms.dto.auth.LoginRequestDTO;
import com.learning.cms.dto.auth.RegisterRequestDTO;
import com.learning.cms.dto.user.UserResponseDTO;
import com.learning.cms.entity.User;
import com.learning.cms.exceptions.ResourceNotFoundException;
import com.learning.cms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO registerUser(RegisterRequestDTO dto) {

        User user = User.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .password(dto.getPassword()) // password encoding if security added later
                .role(dto.getRole())
                .build();

        userRepository.save(user);

        return mapToDTO(user);
    }

    public UserResponseDTO login(LoginRequestDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return mapToDTO(user);
    }

    public UserResponseDTO getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return mapToDTO(user);
    }

    private UserResponseDTO mapToDTO(User user) {

        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setProfilePicture(user.getProfilePicture());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }
}