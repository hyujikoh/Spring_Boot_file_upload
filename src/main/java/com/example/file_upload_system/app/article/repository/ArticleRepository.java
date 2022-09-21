package com.example.file_upload_system.app.article.repository;

import com.example.file_upload_system.app.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}