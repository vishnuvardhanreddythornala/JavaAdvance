package com.learning.cms.dto.material;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MaterialUploadDTO {

    private String title;

    private Long courseId;

    private MultipartFile file;
}