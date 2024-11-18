package com.example.boardproject.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
@Getter @Setter
@Entity @ToString @Slf4j @NoArgsConstructor //엔터티에는 기본생성자가 필수이기 떄문에 Lombok의 어노테이션으로 생성
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id를 PK로 해서 DB가 늘수록 증가한다.
    private String title;
    private String content;

    public Article(Long id, String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void logInfo() {
        log.info("id: {} title: {}, content: {} ", id, title, content);
    }

    // patch method
    public void patch(Article article) {
        if(article.title != null){
            this.title = article.title;
        }
        if(article.content != null) {
            this.content = article.content;
        }
    }
}
