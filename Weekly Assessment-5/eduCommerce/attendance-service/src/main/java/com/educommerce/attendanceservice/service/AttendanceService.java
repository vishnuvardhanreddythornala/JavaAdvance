package com.educommerce.attendanceservice.service;

import com.educommerce.attendanceservice.client.StudentClient;
import com.educommerce.attendanceservice.dto.AttendanceSummaryDto;
import com.educommerce.attendanceservice.dto.StudentDto;
import com.educommerce.attendanceservice.entity.Attendance;
import com.educommerce.attendanceservice.exception.ResourceNotFoundException;
import com.educommerce.attendanceservice.repository.AttendanceRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentClient studentClient;

    @Transactional
    @CircuitBreaker(name = "studentService", fallbackMethod = "markAttendanceFallback")
    public Attendance markAttendance(Attendance attendance) {
        StudentDto student = studentClient.getStudentById(attendance.getStudentId());
        log.info("Marking attendance for student: {}", student.getName());
        return attendanceRepository.save(attendance);
    }

    public Attendance markAttendanceFallback(Attendance attendance, Exception ex) {
        log.warn("Student service unavailable, saving attendance without verification: {}", ex.getMessage());
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceByStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    public List<Attendance> getAttendanceByCourse(Long courseId) {
        return attendanceRepository.findByCourseId(courseId);
    }

    @Transactional
    public Attendance updateAttendance(Long id, Attendance updated) {
        Attendance existing = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance record not found with id: " + id));
        existing.setStatus(updated.getStatus());
        existing.setDate(updated.getDate());
        return attendanceRepository.save(existing);
    }

    @Transactional
    public void deleteAttendance(Long id) {
        if (!attendanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Attendance record not found with id: " + id);
        }
        attendanceRepository.deleteById(id);
    }

    public AttendanceSummaryDto getAttendanceSummary(Long studentId, Long courseId) {
        List<Attendance> records = attendanceRepository.findByStudentIdAndCourseId(studentId, courseId);
        long total = records.size();
        long present = records.stream()
                .filter(a -> a.getStatus() == Attendance.AttendanceStatus.PRESENT)
                .count();
        long absent = total - present;
        double percentage = total == 0 ? 0.0 : (present * 100.0 / total);
        return new AttendanceSummaryDto(studentId, courseId, total, present, absent, percentage);
    }

    public double getAttendancePercentage(Long studentId, Long courseId) {
        return getAttendanceSummary(studentId, courseId).getAttendancePercentage();
    }
}
