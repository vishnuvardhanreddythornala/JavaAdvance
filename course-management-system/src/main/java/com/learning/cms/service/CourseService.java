package com.learning.cms.service;

import com.learning.cms.dto.course.CourseRequestDTO;
import com.learning.cms.dto.course.CourseResponseDTO;
import com.learning.cms.entity.Course;
import com.learning.cms.entity.User;
import com.learning.cms.exceptions.ResourceNotFoundException;
import com.learning.cms.repository.CourseRepository;
import com.learning.cms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @CacheEvict(value = "courses", allEntries = true)
    public CourseResponseDTO createCourse(Long instructorId, CourseRequestDTO dto) {

        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));

        Course course = Course.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .duration(dto.getDuration())
                .level(dto.getLevel())
                .instructor(instructor)
                .build();

        courseRepository.save(course);

        return mapToDTO(course);
    }

    @CacheEvict(value = "courses", allEntries = true)
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO dto) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setPrice(dto.getPrice());
        course.setDuration(dto.getDuration());
        course.setLevel(dto.getLevel());

        courseRepository.save(course);

        return mapToDTO(course);
    }

    @CacheEvict(value = "courses", allEntries = true)
    public void deleteCourse(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        courseRepository.delete(course);
    }

    @Cacheable(value = "courses")
    public Page<CourseResponseDTO> getCourses(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        return courseRepository.findAll(pageable)
                .map(this::mapToDTO);
    }

    public CourseResponseDTO getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        return mapToDTO(course);
    }

    private CourseResponseDTO mapToDTO(Course course) {

        CourseResponseDTO dto = new CourseResponseDTO();

        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setPrice(course.getPrice());
        dto.setDuration(course.getDuration());
        dto.setLevel(course.getLevel());
        dto.setInstructorName(course.getInstructor().getFullName());
        dto.setCreatedAt(course.getCreatedAt());

        return dto;
    }
}