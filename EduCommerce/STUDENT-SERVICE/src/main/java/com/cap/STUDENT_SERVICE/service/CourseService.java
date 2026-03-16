package com.cap.STUDENT_SERVICE.service;

import com.cap.STUDENT_SERVICE.dto.request.CourseRequestDTO;
import com.cap.STUDENT_SERVICE.dto.response.CourseResponseDTO;
import com.cap.STUDENT_SERVICE.entity.Course;
import com.cap.STUDENT_SERVICE.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public CourseResponseDTO createCourse(CourseRequestDTO request){

        Course course = modelMapper.map(request, Course.class);

        Course savedCourse = courseRepository.save(course);

        return modelMapper.map(savedCourse, CourseResponseDTO.class);
    }

    public List<CourseResponseDTO> getAllCourses(){

        return courseRepository.findAll()
                .stream()
                .map(course -> modelMapper.map(course, CourseResponseDTO.class))
                .collect(Collectors.toList());
    }

    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO request){

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setCourseName(request.getCourseName());
        course.setCourseCode(request.getCourseCode());
        course.setInstructor(request.getInstructor());
        course.setCredits(request.getCredits());

        Course updated = courseRepository.save(course);

        return modelMapper.map(updated, CourseResponseDTO.class);
    }

    public void deleteCourse(Long id){

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        courseRepository.delete(course);
    }
}