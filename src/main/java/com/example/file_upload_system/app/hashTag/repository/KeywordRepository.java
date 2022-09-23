package com.example.file_upload_system.app.hashTag.repository;

import com.example.file_upload_system.app.hashTag.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Optional<Keyword> findByContent(String keywordContent);
}