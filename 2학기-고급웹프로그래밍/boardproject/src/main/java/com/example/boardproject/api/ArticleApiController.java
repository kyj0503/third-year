package com.example.boardproject.api;


import com.example.boardproject.DTO.ArticleForm;
import com.example.boardproject.Entity.Article;
import com.example.boardproject.Repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {

    @Autowired
    ArticleRepository articleRepository;

    //조회요청: /api/articles 또는 /api/articles/{id}
    @GetMapping("/api/articles")
    public List<Article> index() {

        //repository로 부터 article list를 받아와서 JSON으로 전송
        List<Article> articles = articleRepository.findAll();
        return articles;
    }

    //단일 게시글 조회
    @GetMapping("/api/articles/{id}")
    public Article read(@PathVariable("id") Long id) {
        Article article = articleRepository.findById(id).orElse(null);

        return article;
    }

    //게시글 생성 요청
    @PostMapping("/api/articels")
    public Article create(@RequestBody ArticleForm articleForm) {
        log.info(articleForm.toString());
        articleForm.setId(-1L); //id -1 (DB insert시 오류 방지)

        Article article = articleForm.toEntity();
        log.info(article.toString());

        Article saved = articleRepository.save(article);

        return saved;
    }

    // : /api/articles/1
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable("id") Long id, @RequestBody ArticleForm articleForm) {
        //1. DTO -> Entity
        Article article = articleForm.toEntity(); //article: 수정할 데이터
        article.logInfo();

        //2. target //target:
        Article target = articleRepository.findById(id).orElse(null);

        //3. 잘못된 요청 처리
        if(target == null || id != article.getId()) {
            log.info(" !: id = {}, article: {}", id, article);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        //4. 업데이트 및 정상응답(200)하기
        target.patch(article); //가존 데이터에 새 데이터 붙이기
        Article updated = articleRepository.save(target); //수정 내용 DB에 최종 저장

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable("id") Long id) {

        //1. 대상 찾기
        Article target =
    }
}
