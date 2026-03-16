package com.cap.STUDENT_SERVICE.security;

import com.cap.STUDENT_SERVICE.entity.Student;
import com.cap.STUDENT_SERVICE.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Student student = studentRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(student.getEmail())
                .password(student.getPassword())
                .roles(student.getRole().name())
                .build();
    }
}
