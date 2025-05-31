package com.example.MiniProjectWeb.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Like 엔티티 클래스
 * - 게시물에 대한 "좋아요" 정보를 저장하는 엔티티
 * - "좋아요"를 누른 사용자, 해당 게시물, 생성 시간을 포함
 */
@Entity
@Table(name = "likes") // 데이터베이스의 "likes" 테이블과 매핑
@Data
public class Like {

    /** 좋아요의 고유 ID (Primary Key) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 설정
    private Long id;

    /**
     * 좋아요를 누른 사용자
     * - User 엔티티와 다대일 관계 (ManyToOne)
     * - "user_id" 외래 키를 통해 User 테이블과 연결
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // 외래 키 설정 및 null 값 허용 불가
    private User user;

    /**
     * 좋아요가 눌린 게시물
     * - Post 엔티티와 다대일 관계 (ManyToOne)
     * - "post_id" 외래 키를 통해 Post 테이블과 연결
     */
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false) // 외래 키 설정 및 null 값 허용 불가
    private Post post;

    /** 좋아요가 생성된 시간 */
    @Column(nullable = false) // null 값 허용 불가
    private LocalDateTime createdAt = LocalDateTime.now(); // 생성 시 현재 시간으로 초기화
}
