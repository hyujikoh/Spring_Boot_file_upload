package com.example.file_upload_system.app.fileUpload.repository;

import com.example.file_upload_system.app.fileUpload.GenFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenFileRepository extends JpaRepository<GenFile, Long> {
}