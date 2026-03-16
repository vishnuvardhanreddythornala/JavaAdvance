package com.cap.RESULT_SERVICE.service;

import com.cap.RESULT_SERVICE.client.AttendanceClient;
import com.cap.RESULT_SERVICE.client.StudentClient;
import com.cap.RESULT_SERVICE.dto.ResultRequestDTO;
import com.cap.RESULT_SERVICE.dto.AttendanceResponseDTO;
import com.cap.RESULT_SERVICE.dto.ResultResponseDTO;
import com.cap.RESULT_SERVICE.entity.Result;
import com.cap.RESULT_SERVICE.repository.ResultRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;
    private final StudentClient studentClient;
    private final AttendanceClient attendanceClient;
    private final ModelMapper modelMapper;

    @CircuitBreaker(name = "attendanceService", fallbackMethod = "attendanceFallback")
    public ResultResponseDTO createResult(ResultRequestDTO request){

        // verify student exists
        studentClient.getStudentById(request.getStudentId());

        // fetch attendance
        List<AttendanceResponseDTO> attendanceList =
                attendanceClient.getAttendanceByStudent(request.getStudentId());

        double percentage = calculatePercentage(
                request.getMarksObtained(),
                request.getMaxMarks()
        );

        String grade = calculateGrade(percentage);

        Result result = Result.builder()
                .studentId(request.getStudentId())
                .courseId(request.getCourseId())
                .examType(request.getExamType())
                .marksObtained(request.getMarksObtained())
                .maxMarks(request.getMaxMarks())
                .grade(grade)
                .build();

        Result saved = resultRepository.save(result);

        return modelMapper.map(saved, ResultResponseDTO.class);
    }

    public List<ResultResponseDTO> getResultsByStudent(Long studentId){

        return resultRepository.findByStudentId(studentId)
                .stream()
                .map(result -> modelMapper.map(result, ResultResponseDTO.class))
                .collect(Collectors.toList());
    }

    public List<ResultResponseDTO> getResultsByCourse(Long courseId){

        return resultRepository.findByCourseId(courseId)
                .stream()
                .map(result -> modelMapper.map(result, ResultResponseDTO.class))
                .collect(Collectors.toList());
    }

    public ResultResponseDTO updateResult(Long id, ResultRequestDTO request){

        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result not found"));

        double percentage = calculatePercentage(
                request.getMarksObtained(),
                request.getMaxMarks()
        );

        result.setMarksObtained(request.getMarksObtained());
        result.setMaxMarks(request.getMaxMarks());
        result.setExamType(request.getExamType());
        result.setGrade(calculateGrade(percentage));

        Result updated = resultRepository.save(result);

        return modelMapper.map(updated, ResultResponseDTO.class);
    }

    public void deleteResult(Long id){

        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result not found"));

        resultRepository.delete(result);
    }

    private double calculatePercentage(double marks, double maxMarks){
        return (marks / maxMarks) * 100;
    }

    private String calculateGrade(double percentage){

        if(percentage >= 90) return "A";
        if(percentage >= 80) return "B";
        if(percentage >= 70) return "C";
        if(percentage >= 60) return "D";

        return "F";
    }

    public ResultResponseDTO attendanceFallback(
            ResultRequestDTO request,
            Throwable throwable){

        throw new RuntimeException(
                "Attendance service unavailable. Cannot compute performance."
        );
    }
}