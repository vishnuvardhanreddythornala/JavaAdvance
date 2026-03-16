package com.cap.STUDENT_SERVICE.service;

import com.cap.STUDENT_SERVICE.dto.response.StudentResponseDTO;
import com.cap.STUDENT_SERVICE.entity.Student;
import com.cap.STUDENT_SERVICE.exceptions.StudentNotFoundException;
import com.cap.STUDENT_SERVICE.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public List<StudentResponseDTO> getAllStudents(){

        return studentRepository.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentResponseDTO.class))
                .collect(Collectors.toList());
    }

    public StudentResponseDTO getStudentById(Long id){

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        return modelMapper.map(student, StudentResponseDTO.class);
    }

    public StudentResponseDTO updateStudent(Long id, Student updatedStudent){

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        student.setName(updatedStudent.getName());
        student.setDepartment(updatedStudent.getDepartment());
        student.setSemester(updatedStudent.getSemester());

        Student savedStudent = studentRepository.save(student);

        return modelMapper.map(savedStudent, StudentResponseDTO.class);
    }

    public StudentResponseDTO deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found"));

        studentRepository.delete(student);
        return modelMapper.map(student, StudentResponseDTO.class);
    }
}