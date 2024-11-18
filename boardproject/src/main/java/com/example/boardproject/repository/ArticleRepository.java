package com.example.boardproject.Repository;

import com.example.boardproject.Entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    public List<Article> findAll();
}

