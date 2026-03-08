package com.cap.BookStroreRest.Controller;

import com.cap.BookStroreRest.DataTransferObject.FileDto;
import com.cap.BookStroreRest.Entity.FileEntity;
import com.cap.BookStroreRest.Service.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/files")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileDto> uploadFile(
            @RequestParam("file")MultipartFile file) throws IOException {
        FileDto uploadedFile =  fileService.uploadFile(file);
        return ResponseEntity.ok(uploadedFile);

    }
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id){
        FileEntity fileEntity = fileService.downloadFile(id);
        ByteArrayResource resource =  new ByteArrayResource(fileEntity.getFileData());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileEntity.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + fileEntity.getFileName() + "\"")
                .contentLength(fileEntity.getFileSize())
                .body(resource);
    }
}
