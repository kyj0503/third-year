package com.example.boardproject.DTO;

import com.example.boardproject.Entity.Article;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @Slf4j
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
    public void loginfo() {
        log.info("title: {}, content: {} ", title, content);
    }
}
