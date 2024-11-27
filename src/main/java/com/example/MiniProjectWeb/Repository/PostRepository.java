package com.example.MiniProjectWeb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.MiniProjectWeb.Entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {}
