package com.cap.BookStroreRest.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Files")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String fileName;
    private String  fileType;
    private Long fileSize;

    @Lob
    @Column(name = "file_data")
    private byte[] fileData;

    private LocalDateTime uploadedAt;

    @PrePersist
    public void prePersist(){
        uploadedAt =  LocalDateTime.now();
    }


}
