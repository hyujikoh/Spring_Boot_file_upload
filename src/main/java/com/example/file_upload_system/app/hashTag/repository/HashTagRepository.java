package com.example.file_upload_system.app.hashTag.repository;

import com.example.file_upload_system.app.hashTag.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {
    Optional<HashTag> findByArticleIdAndKeywordId(Long articleId, Long keywordId);
    List<HashTag> findAllByArticleId(Long articleId);
}