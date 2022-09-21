package com.example.file_upload_system.app.article.service;

import com.example.file_upload_system.app.article.entity.Article;
import com.example.file_upload_system.app.article.repository.ArticleRepository;
import com.example.file_upload_system.app.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ArticleService {


    private final ArticleRepository articleRepository;

    public Article write(Long authorId, String subject, String content) {
        Article article = Article
                .builder()
                .author(new Member(authorId))
                .subject(subject)
                .content(content)
                .build();

        articleRepository.save(article);

        return article;
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null); // 없을경우 null 값
    }
}
