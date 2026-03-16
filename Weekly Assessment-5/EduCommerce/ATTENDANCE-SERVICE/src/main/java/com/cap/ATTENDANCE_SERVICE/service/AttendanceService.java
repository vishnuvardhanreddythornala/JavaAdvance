package com.cap.ATTENDANCE_SERVICE.service;

import com.cap.ATTENDANCE_SERVICE.client.StudentClient;
import com.cap.ATTENDANCE_SERVICE.dto.AttendanceRequestDTO;
import com.cap.ATTENDANCE_SERVICE.dto.AttendanceResponseDTO;
import com.cap.ATTENDANCE_SERVICE.entity.Attendance;
import com.cap.ATTENDANCE_SERVICE.exceptions.StudentNotFoundException;
import com.cap.ATTENDANCE_SERVICE.repository.AttendanceRepository;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentClient studentClient;
    private final ModelMapper modelMapper;

    @CircuitBreaker(name = "studentService", fallbackMethod = "studentFallback")
    public AttendanceResponseDTO markAttendance(AttendanceRequestDTO request){

        studentClient.getStudentById(request.getStudentId());

        Attendance attendance = Attendance.builder()
                .studentId(request.getStudentId())
                .courseId(request.getCourseId())
                .date(LocalDate.now())
                .status(request.getStatus())
                .build();

        Attendance saved = attendanceRepository.save(attendance);

        return modelMapper.map(saved, AttendanceResponseDTO.class);
    }

    public AttendanceResponseDTO studentFallback(
            AttendanceRequestDTO request,
            Throwable throwable){

        throw new RuntimeException(
                "Student verification service unavailable. Please try again later."
        );
    }

    public List<AttendanceResponseDTO> getAttendanceByStudent(Long studentId){

        return attendanceRepository.findByStudentId(studentId)
                .stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceResponseDTO.class))
                .collect(Collectors.toList());
    }

    public List<AttendanceResponseDTO> getAttendanceByCourse(Long courseId){

        return attendanceRepository.findByCourseId(courseId).stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceResponseDTO.class))
                .collect(Collectors.toList());
    }
}