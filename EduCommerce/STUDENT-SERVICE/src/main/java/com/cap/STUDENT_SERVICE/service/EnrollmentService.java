package com.cap.STUDENT_SERVICE.service;

import com.cap.STUDENT_SERVICE.dto.request.EnrollmentRequestDTO;
import com.cap.STUDENT_SERVICE.dto.response.CourseResponseDTO;
import com.cap.STUDENT_SERVICE.entity.Course;
import com.cap.STUDENT_SERVICE.entity.Enrollment;
import com.cap.STUDENT_SERVICE.repository.CourseRepository;
import com.cap.STUDENT_SERVICE.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public String enrollStudent(EnrollmentRequestDTO request){

        Enrollment enrollment = Enrollment.builder()
                .studentId(request.getStudentId())
                .courseId(request.getCourseId())
                .enrollmentDate(LocalDate.now())
                .build();

        enrollmentRepository.save(enrollment);

        return "Student enrolled successfully";
    }

    public List<CourseResponseDTO> getCoursesByStudentId(Long studentId){

        List<Enrollment> enrollments =
                enrollmentRepository.findByStudentId(studentId);

        return enrollments.stream()
                .map(enrollment ->
                        courseRepository.findById(enrollment.getCourseId())
                                .orElseThrow(() -> new RuntimeException("Course not found"))
                )
                .map(course -> modelMapper.map(course, CourseResponseDTO.class))
                .collect(Collectors.toList());
    }
}