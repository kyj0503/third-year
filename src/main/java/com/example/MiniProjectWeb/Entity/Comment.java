package com.example.MiniProjectWeb.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Comment 엔티티 클래스
 * - 게시물에 작성된 댓글 정보를 저장하는 엔티티
 * - 댓글의 내용, 작성자, 작성된 게시물, 작성 시간 등의 정보를 포함
 */
@Entity
@Table(name = "comments") // 데이터베이스의 "comments" 테이블과 매핑
@Data
public class Comment {

    /** 댓글의 고유 ID (Primary Key) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 설정
    private Long id;

    /** 댓글의 내용 */
    @Column(nullable = false) // null 값 허용 불가
    private String content;

    /**
     * 댓글 작성자
     * - User 엔티티와 다대일 관계 (ManyToOne)
     * - "user_id" 외래 키를 통해 User 테이블과 연결
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // 외래 키 설정 및 null 값 허용 불가
    private User user;

    /**
     * 댓글이 작성된 게시물
     * - Post 엔티티와 다대일 관계 (ManyToOne)
     * - "post_id" 외래 키를 통해 Post 테이블과 연결
     */
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false) // 외래 키 설정 및 null 값 허용 불가
    private Post post;

    /** 댓글이 작성된 시간 */
    @Column(nullable = false) // null 값 허용 불가
    private LocalDateTime createdAt = LocalDateTime.now(); // 생성 시 현재 시간으로 초기화

    /**
     * 댓글 소유자 여부를 나타내는 필드
     * - 현재 세션의 사용자와 댓글 작성자가 동일한지 확인
     * - 데이터베이스에는 저장되지 않는 필드 (Transient)
     */
    @Transient
    private boolean isOwner;

    // Getter와 Setter 메서드

    /**
     * 댓글 소유자 여부 반환
     * @return 현재 사용자가 댓글 작성자인지 여부
     */
    public boolean isOwner() {
        return isOwner;
    }

    /**
     * 댓글 소유자 여부 설정
     * @param isOwner 댓글 소유자 여부를 설정하는 값
     */
    public void setIsOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }
}
