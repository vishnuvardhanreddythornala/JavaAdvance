package com.educommerce.resultservice.service;

import com.educommerce.resultservice.client.AttendanceClient;
import com.educommerce.resultservice.client.StudentClient;
import com.educommerce.resultservice.dto.AttendanceSummaryDto;
import com.educommerce.resultservice.dto.StudentDto;
import com.educommerce.resultservice.dto.StudentReportDto;
import com.educommerce.resultservice.entity.Result;
import com.educommerce.resultservice.exception.ResourceNotFoundException;
import com.educommerce.resultservice.repository.ResultRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResultService {

    private final ResultRepository resultRepository;
    private final StudentClient studentClient;
    private final AttendanceClient attendanceClient;

    @Transactional
    public Result createResult(Result result) {
        return resultRepository.save(result);
    }

    public List<Result> getResultsByStudent(Long studentId) {
        return resultRepository.findByStudentId(studentId);
    }

    public List<Result> getResultsByCourse(Long courseId) {
        return resultRepository.findByCourseId(courseId);
    }

    @Transactional
    public Result updateResult(Long id, Result updated) {
        Result existing = resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found with id: " + id));
        existing.setExamType(updated.getExamType());
        existing.setMarksObtained(updated.getMarksObtained());
        existing.setMaxMarks(updated.getMaxMarks());
        return resultRepository.save(existing);
    }

    @Transactional
    public void deleteResult(Long id) {
        if (!resultRepository.existsById(id)) {
            throw new ResourceNotFoundException("Result not found with id: " + id);
        }
        resultRepository.deleteById(id);
    }

    public double calculateGpa(Long studentId) {
        List<Result> results = resultRepository.findByStudentId(studentId);
        if (results.isEmpty()) return 0.0;
        double totalPoints = results.stream()
                .mapToDouble(r -> {
                    double pct = (r.getMarksObtained() / r.getMaxMarks()) * 100;
                    if (pct >= 90) return 4.0;
                    else if (pct >= 80) return 3.7;
                    else if (pct >= 70) return 3.3;
                    else if (pct >= 60) return 3.0;
                    else if (pct >= 50) return 2.0;
                    else return 0.0;
                })
                .sum();
        return totalPoints / results.size();
    }

    @CircuitBreaker(name = "studentService", fallbackMethod = "getStudentReportFallback")
    public StudentReportDto getStudentReport(Long studentId) {
        StudentDto student = studentClient.getStudentById(studentId);
        List<Result> results = resultRepository.findByStudentId(studentId);
        double gpa = calculateGpa(studentId);

        String attendanceInfo = "Attendance data unavailable";
        if (!results.isEmpty()) {
            try {
                AttendanceSummaryDto summary = attendanceClient.getAttendanceSummary(
                        studentId, results.get(0).getCourseId());
                attendanceInfo = String.format("%.1f%% attendance", summary.getAttendancePercentage());
            } catch (Exception e) {
                log.warn("Could not fetch attendance for student {}: {}", studentId, e.getMessage());
            }
        }

        return new StudentReportDto(student, results, gpa, attendanceInfo);
    }

    public StudentReportDto getStudentReportFallback(Long studentId, Exception ex) {
        log.warn("Circuit breaker triggered for student report {}: {}", studentId, ex.getMessage());
        List<Result> results = resultRepository.findByStudentId(studentId);
        double gpa = calculateGpa(studentId);
        StudentDto fallbackStudent = new StudentDto();
        fallbackStudent.setId(studentId);
        fallbackStudent.setName("Student data currently unavailable");
        return new StudentReportDto(fallbackStudent, results, gpa, "Attendance data currently unavailable");
    }
}
