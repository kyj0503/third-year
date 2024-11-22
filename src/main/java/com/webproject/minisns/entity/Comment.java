package com.webproject.minisns.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id") // DB의 컬럼명과 매핑
    private Integer commentId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false) // 외래키 매핑
    private Post post;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false) // 외래키 매핑
    private Player player;
}
