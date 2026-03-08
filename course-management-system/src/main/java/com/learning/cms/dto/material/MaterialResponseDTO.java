package com.learning.cms.dto.material;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MaterialResponseDTO {

    private Long id;

    private String title;

    private String fileName;

    private String fileType;

    private String fileUrl;

    private LocalDateTime uploadDate;
}