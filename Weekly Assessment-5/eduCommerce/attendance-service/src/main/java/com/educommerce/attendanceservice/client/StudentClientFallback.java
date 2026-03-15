package com.educommerce.attendanceservice.client;

import com.educommerce.attendanceservice.dto.StudentDto;
import org.springframework.stereotype.Component;

@Component
public class StudentClientFallback implements StudentClient {

    @Override
    public StudentDto getStudentById(Long id) {
        StudentDto fallback = new StudentDto();
        fallback.setId(id);
        fallback.setName("Student data currently unavailable");
        fallback.setEmail("unavailable@fallback.com");
        return fallback;
    }
}
