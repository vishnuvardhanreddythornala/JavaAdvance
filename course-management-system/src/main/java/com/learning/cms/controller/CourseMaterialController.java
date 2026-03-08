package com.learning.cms.controller;

import com.learning.cms.dto.material.MaterialResponseDTO;
import com.learning.cms.dto.material.MaterialUploadDTO;
import com.learning.cms.entity.CourseMaterial;
import com.learning.cms.exceptions.ResourceNotFoundException;
import com.learning.cms.repository.CourseMaterialRepository;
import com.learning.cms.service.CourseMaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
@Tag(name = "CourseMaterial", description = "API's related to course material management")
public class CourseMaterialController {

    private final CourseMaterialService materialService;
    private final CourseMaterialRepository materialRepository;

    @PostMapping("/upload")
    @Operation(summary = "Upload course material")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Material uploaded successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found"),
            @ApiResponse(responseCode = "500", description = "File upload failed")
    })
    public ResponseEntity<MaterialResponseDTO> uploadMaterial(
            @ModelAttribute MaterialUploadDTO dto
    ) {

        MaterialResponseDTO response = materialService.uploadMaterial(dto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/download")
    @Operation(summary = "Download course material file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "File downloaded successfully"),
            @ApiResponse(responseCode = "404", description = "Material not found")
    })
    public ResponseEntity<Resource> downloadMaterial(@PathVariable Long id) {

        CourseMaterial material = materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found"));

        try {

            Path path = Paths.get(material.getFileUrl());
            Resource resource = new UrlResource(path.toUri());

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(material.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + material.getFileName() + "\"")
                    .body(resource);

        } catch (Exception e) {
            throw new RuntimeException("File download failed");
        }
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Get all materials for a course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Materials retrieved successfully")
    })
    public ResponseEntity<List<MaterialResponseDTO>> getCourseMaterials(
            @PathVariable Long courseId
    ) {

        List<MaterialResponseDTO> materials =
                materialService.getMaterialsByCourse(courseId);

        return ResponseEntity.ok(materials);
    }
}