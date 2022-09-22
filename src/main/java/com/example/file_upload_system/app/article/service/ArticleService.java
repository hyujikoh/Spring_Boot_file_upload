package com.example.file_upload_system.app.article.service;

import com.example.file_upload_system.app.article.entity.Article;
import com.example.file_upload_system.app.article.repository.ArticleRepository;
import com.example.file_upload_system.app.fileUpload.GenFile;
import com.example.file_upload_system.app.fileUpload.service.GenFileService;
import com.example.file_upload_system.app.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class ArticleService {


    private final ArticleRepository articleRepository;
    private final GenFileService genFileService;


    public Article write(Long authorId, String subject, String content) {
        return write(new Member(authorId), subject, content);
    }

    public Article write(Member author, String subject, String content) {
        Article article = Article
                .builder()
                .author(author)
                .subject(subject)
                .content(content)
                .build();

        articleRepository.save(article);

        return article;
    }
    public void addGenFileByUrl(Article article, String typeCode, String type2Code, int fileNo, String url) {
        genFileService.addGenFileByUrl("article", article.getId(), typeCode, type2Code, fileNo, url);
    }
    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null); // 없을경우 null 값
    }

    public Article getForPrintArticleById(Long id) {

        Article article = getArticleById(id);
        Map<String, GenFile> genFileMap = genFileService.getRelGenFileMap(article);

        article.getExtra().put("age", 22);

        article.getExtra().put("genFileMap", genFileMap);
        return article;
    }
}
