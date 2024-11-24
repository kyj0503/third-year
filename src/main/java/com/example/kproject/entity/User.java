package com.example.kproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    /**
     * 사용자 고유 ID.
     * 데이터베이스에서 자동으로 생성되는 기본 키이다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    /**
     * 사용자 계정 이름.
     * 데이터베이스에서 유일성을 보장하며 Null 값을 허용하지 않는다.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * 사용자 비밀번호.
     * 암호화된 상태로 저장되며 Null 값을 허용하지 않는다.
     */
    @Column(nullable = false)
    private String password;

    /**
     * 사용자 이메일 주소.
     * 데이터베이스에서 유일성을 보장하며 Null 값을 허용하지 않는다.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * 계정 생성 시간.
     * 계정 생성 시 자동으로 설정되며 이후 수정할 수 없다.
     */
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    /**
     * 엔티티가 데이터베이스에 persist되기 전에 호출된다.
     * `createdAt` 필드를 현재 시간으로 설정한다.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    /**
     * 사용자와 연결된 리뷰 목록.
     * 사용자 삭제 시 연관된 리뷰도 함께 삭제된다.
     */
    @Getter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}
