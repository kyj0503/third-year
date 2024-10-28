package com.example.boardproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @Autowired
    //스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입(DI)
    ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm articleForm){
        articleForm.logInfo();
        //1. DTO를 Entity로 변환
        Article article = articleForm.toEntity();
        article.logInfo();
        //2. repository로 엔터티를 저장
        Article saved = articleRepository.save(article);
        saved.logInfo();
        return "";
    }
}
