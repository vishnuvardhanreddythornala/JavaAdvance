package com.learning.cms.service;

import com.learning.cms.dto.material.MaterialResponseDTO;
import com.learning.cms.dto.material.MaterialUploadDTO;
import com.learning.cms.entity.Course;
import com.learning.cms.entity.CourseMaterial;
import com.learning.cms.exceptions.FileStorageException;
import com.learning.cms.exceptions.ResourceNotFoundException;
import com.learning.cms.repository.CourseMaterialRepository;
import com.learning.cms.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseMaterialService {

    private final CourseRepository courseRepository;
    private final CourseMaterialRepository materialRepository;

    public MaterialResponseDTO uploadMaterial(MaterialUploadDTO dto) {

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        MultipartFile file = dto.getFile();

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path uploadDir = Paths.get("uploads");
        Path uploadPath = uploadDir.resolve(fileName);

        try {

            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            Files.copy(file.getInputStream(), uploadPath);

        } catch (Exception e) {
            throw new FileStorageException("File upload failed");
        }

        CourseMaterial material = CourseMaterial.builder()
                .title(dto.getTitle())
                .fileName(fileName)
                .fileType(file.getContentType())
                .fileUrl(uploadPath.toString())
                .uploadDate(LocalDateTime.now())
                .course(course)
                .build();

        materialRepository.save(material);

        return mapToDTO(material);
    }

    public List<MaterialResponseDTO> getMaterialsByCourse(Long courseId) {

        return materialRepository.findByCourseId(courseId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private MaterialResponseDTO mapToDTO(CourseMaterial material) {

        MaterialResponseDTO dto = new MaterialResponseDTO();

        dto.setId(material.getId());
        dto.setTitle(material.getTitle());
        dto.setFileName(material.getFileName());
        dto.setFileType(material.getFileType());
        dto.setFileUrl(material.getFileUrl());
        dto.setUploadDate(material.getUploadDate());

        return dto;
    }
}