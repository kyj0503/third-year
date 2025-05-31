package com.example.MiniProjectWeb.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Post 엔티티 클래스
 * - 게시물 정보를 저장하는 엔티티
 * - 게시물 작성자, 내용, 댓글, 좋아요 개수, 생성 시간 등의 정보를 포함
 */
@Entity
@Table(name = "posts") // 데이터베이스의 "posts" 테이블과 매핑
@Data
public class Post {

    /** 게시물의 고유 ID (Primary Key) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 설정
    private Long id;

    /** 게시물 내용 */
    @Column(nullable = false) // null 값 허용 불가
    private String content;

    /**
     * 게시물 작성자
     * - User 엔티티와 다대일 관계 (ManyToOne)
     * - "user_id" 외래 키를 통해 User 테이블과 연결
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // 외래 키 설정 및 null 값 허용 불가
    private User user;

    /**
     * 게시물에 달린 댓글 목록
     * - Comment 엔티티와 일대다 관계 (OneToMany)
     * - "post" 필드로 연결되며, 댓글 삭제 시 연관된 데이터도 자동 삭제 (orphanRemoval)
     */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    /** 게시물의 좋아요 개수 (데이터베이스에 저장되지 않음) */
    @Transient // 영속성 컨텍스트에 포함되지 않음
    private long likeCount;

    /** 좋아요 개수 getter */
    public long getLikeCount() {
        return likeCount;
    }

    /** 좋아요 개수 setter */
    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    /** 현재 사용자가 게시물의 소유자인지 여부 (데이터베이스에 저장되지 않음) */
    @Transient // 영속성 컨텍스트에 포함되지 않음
    private boolean isOwner;

    /** 소유자 여부 getter */
    public boolean isOwner() {
        return isOwner;
    }

    /** 소유자 여부 setter */
    public void setIsOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }

    /** 에러 메시지 전달용 필드 (데이터베이스에 저장되지 않음) */
    @Transient // 영속성 컨텍스트에 포함되지 않음
    @Getter
    @Setter
    private String error;

    /** 게시물 생성 시간 */
    @Column(nullable = false) // null 값 허용 불가
    private LocalDateTime createdAt = LocalDateTime.now(); // 생성 시 현재 시간으로 초기화

    @Transient
    private int commentCount; // 댓글 개수 필드

    public int getCommentCount() {
        return comments.size(); // 댓글 리스트 크기로 댓글 개수 반환
    }

    @Transient
    private boolean isLiked;

    public boolean isLiked() {
        return isLiked;
    }

    public void setIsLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }

}
