package com.example.file_upload_system.app.hashTag.entity;

import com.example.file_upload_system.app.article.entity.Article;
import com.example.file_upload_system.app.base.entity.BaseEntity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class HashTag extends BaseEntity {
    @ManyToOne
    private Article article;
    @ManyToOne
    private Keyword keyword;
}