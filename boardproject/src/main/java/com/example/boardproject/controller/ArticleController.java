package com.example.boardproject.Controller;

import com.example.boardproject.Entity.Article;
import com.example.boardproject.DTO.ArticleForm;
import com.example.boardproject.Repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("articles/create")
    public String createArticle(ArticleForm articleForm) {
        articleForm.loginfo();

        //1. DTO를 Entity로 변환
        Article article = articleForm.toEntity();
        article.logInfo();

        //2, repository로 엔터티를 저장
        Article saved = articleRepository.save(article);
        saved.logInfo();

        return "redirect:/articles/" + saved.getId();
    }

    //id를 통한 단일 데이터 조회 요청 접수
    @GetMapping("articles/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        log.info("id = {} ", id);

        //1. id를 조회하여 article 데이터 가져오기
        //Optional<Article> article = articleRepository.findById(id);
        Article article = articleRepository.findById(id).orElse(null);

        //2. 모델에 등록하기
        model.addAttribute("article", article);

        //3. 뷰 페이지 반환
        return "articles/show";
    }

    @GetMapping("/articles")
    public String list(Model model) {
        //1. 모든 데이터 가져오기
        List<Article> articleList = articleRepository.findAll();

        //2. Model에 데이터 등록
        model.addAttribute("articleList", articleList);

        //3. View Page 설정
        return "articles/index";
    }

    //데이터 수정을 위해서 수정 데이터 받아오기
    @PostMapping("/articles/update")
    public String update(ArticleForm articleForm) {
        log.info(articleForm.toString());       //DTO
        //DTO -> Entity
        Article articleEntity = articleForm.toEntity();
        log.info(articleEntity.toString());     //entity
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if (target != null) {
            articleRepository.save(articleEntity);
        }
        return "redirect:/articles/" + target.getId();
    }

    //데이터 수정을 위해서 원본 데이터를 수정한다.
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);

        return "articles/edit";
    }

    //데이터 삭제
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes rttr) {
        Article target = articleRepository.findById(id).orElse(null);
        if (target != null) {
            articleRepository.deleteById(id);
            //삭제 완료 메세지
            rttr.addFlashAttribute("msg", "삭제되었습니다.");
        }

        return "redirect:/articles";
    }
}
