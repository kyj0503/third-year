package com.example.MiniProjectWeb.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content; // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 댓글 작성자

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // 댓글이 속한 게시물

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
