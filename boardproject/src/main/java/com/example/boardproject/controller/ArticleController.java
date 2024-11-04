package com.example.boardproject.controller;

import com.example.boardproject.entity.Article;
import com.example.boardproject.ArticleForm;
import com.example.boardproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
    //article 리스트 출력
    @GetMapping("/articles")
    public String list(Model model) {
        //1. 모든 데이터 가져오기
        List<Article> articleList = articleRepository.findAll();
        //2. Model에 데이터 등록
        model.addAttribute("articleList", articleList);
        //3. 뷰 페이지 설정
        return "articles/index";
    }
}
