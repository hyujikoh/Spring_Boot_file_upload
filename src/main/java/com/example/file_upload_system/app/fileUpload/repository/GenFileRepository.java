package com.example.file_upload_system.app.fileUpload.repository;

import com.example.file_upload_system.app.fileUpload.GenFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenFileRepository extends JpaRepository<GenFile, Long> {
    List<GenFile> findByRelTypeCodeAndRelId(String article, Long id);

    List<GenFile> findByRelTypeCodeAndRelIdOrderByTypeCodeAscType2CodeAscFileNoAsc(String relTypeCode, Long relId);
}