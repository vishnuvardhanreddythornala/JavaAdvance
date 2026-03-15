package com.educommerce.studentservice.service;

import com.educommerce.studentservice.dto.AuthDto;
import com.educommerce.studentservice.dto.StudentDto;
import com.educommerce.studentservice.entity.Course;
import com.educommerce.studentservice.entity.Enrollment;
import com.educommerce.studentservice.entity.Student;
import com.educommerce.studentservice.exception.DuplicateResourceException;
import com.educommerce.studentservice.exception.ResourceNotFoundException;
import com.educommerce.studentservice.repository.CourseRepository;
import com.educommerce.studentservice.repository.EnrollmentRepository;
import com.educommerce.studentservice.repository.StudentRepository;
import com.educommerce.studentservice.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public AuthDto.AuthResponse register(AuthDto.RegisterRequest request) {
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already registered: " + request.getEmail());
        }
        Student student = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .department(request.getDepartment())
                .semester(request.getSemester())
                .build();
        student = studentRepository.save(student);
        String token = jwtUtil.generateToken(student.getEmail(), student.getId());
        return new AuthDto.AuthResponse(token, student.getId(), student.getEmail(), student.getName());
    }

    public AuthDto.AuthResponse login(AuthDto.LoginRequest request) {
        Student student = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid email or password"));
        if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
            throw new ResourceNotFoundException("Invalid email or password");
        }
        String token = jwtUtil.generateToken(student.getEmail(), student.getId());
        return new AuthDto.AuthResponse(token, student.getId(), student.getEmail(), student.getName());
    }

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream().map(this::toDto).toList();
    }

    public StudentDto getStudentById(Long id) {
        return toDto(findStudentById(id));
    }

    @Transactional
    public StudentDto updateStudent(Long id, StudentDto dto) {
        Student student = findStudentById(id);
        student.setName(dto.getName());
        student.setDepartment(dto.getDepartment());
        student.setSemester(dto.getSemester());
        return toDto(studentRepository.save(student));
    }

    @Transactional
    public void deleteStudent(Long id) {
        findStudentById(id);
        studentRepository.deleteById(id);
    }

    @Transactional
    public Course createCourse(Course course) {
        if (courseRepository.existsByCourseCode(course.getCourseCode())) {
            throw new DuplicateResourceException("Course code already exists: " + course.getCourseCode());
        }
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Transactional
    public Course updateCourse(Long id, Course course) {
        Course existing = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        existing.setCourseName(course.getCourseName());
        existing.setInstructor(course.getInstructor());
        existing.setCredits(course.getCredits());
        return courseRepository.save(existing);
    }

    @Transactional
    public void deleteCourse(Long id) {
        courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        courseRepository.deleteById(id);
    }

    @Transactional
    public Enrollment enrollStudent(Long studentId, Long courseId) {
        findStudentById(studentId);
        courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        if (enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new DuplicateResourceException("Student already enrolled in this course");
        }
        Enrollment enrollment = Enrollment.builder()
                .studentId(studentId)
                .courseId(courseId)
                .build();
        return enrollmentRepository.save(enrollment);
    }

    public List<Course> getStudentCourses(Long studentId) {
        findStudentById(studentId);
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        return enrollments.stream()
                .map(e -> courseRepository.findById(e.getCourseId()).orElse(null))
                .filter(c -> c != null)
                .toList();
    }

    private Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    private StudentDto toDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setDepartment(student.getDepartment());
        dto.setSemester(student.getSemester());
        dto.setCreatedAt(student.getCreatedAt());
        return dto;
    }
}
