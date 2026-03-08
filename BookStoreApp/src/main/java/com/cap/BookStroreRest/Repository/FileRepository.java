package com.cap.BookStroreRest.Repository;

import com.cap.BookStroreRest.Entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
