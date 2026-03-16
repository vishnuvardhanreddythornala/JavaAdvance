package com.cap.STUDENT_SERVICE.service;

import com.cap.STUDENT_SERVICE.dto.request.LoginRequestDTO;
import com.cap.STUDENT_SERVICE.dto.request.RegisterRequestDTO;
import com.cap.STUDENT_SERVICE.dto.response.AuthResponseDTO;
import com.cap.STUDENT_SERVICE.dto.response.RegisterResponseDTO;
import com.cap.STUDENT_SERVICE.entity.Student;
import com.cap.STUDENT_SERVICE.repository.StudentRepository;
import com.cap.STUDENT_SERVICE.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public RegisterResponseDTO register(RegisterRequestDTO request){

        if(studentRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already registered");
        }

        Student student = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .department(request.getDepartment())
                .semester(request.getSemester())
                .role(request.getRole())
                .createdAt(LocalDateTime.now())
                .build();

        studentRepository.save(student);

        return RegisterResponseDTO.builder()
                .message("Registration successful")
                .email(student.getEmail())
                .build();
    }

    public AuthResponseDTO login(LoginRequestDTO request){

        Student student = studentRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if(!passwordEncoder.matches(
                request.getPassword(),
                student.getPassword()
        )){
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(
                student.getEmail(),
                student.getRole()
        );

        return AuthResponseDTO.builder()
                .token(token)
                .email(student.getEmail())
                .role(student.getRole())
                .build();
    }
}