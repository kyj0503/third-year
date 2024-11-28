package com.example.MiniProjectWeb.Repository;

import com.example.MiniProjectWeb.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {}
