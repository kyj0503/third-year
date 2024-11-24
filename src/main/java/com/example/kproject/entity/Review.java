package com.example.kproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Review 엔티티는 사용자 리뷰 정보를 저장하는 데이터베이스 테이블과 매핑된다.
 * 리뷰는 특정 장소(Place)에 대해 작성되며, 작성한 사용자(User)와 연관된다.
 */
@Entity
@Getter
@Setter
public class Review {

    /**
     * 리뷰의 고유 ID.
     * 데이터베이스에서 자동으로 생성되는 기본 키 값이다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    /**
     * 리뷰를 작성한 사용자와의 관계.
     * User 테이블과 다대일(@ManyToOne) 관계를 맺고 있다.
     * userId는 외래 키(foreign key)로 사용되며, Null 값을 허용하지 않는다.
     */
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    /**
     * 리뷰가 작성된 장소와의 관계.
     * Place 테이블과 다대일(@ManyToOne) 관계를 맺고 있다.
     * placeId는 외래 키(foreign key)로 사용되며, Null 값을 허용하지 않는다.
     */
    @ManyToOne
    @JoinColumn(name = "placeId", nullable = false)
    private Place place;

    /**
     * 리뷰 평점.
     * 점수는 1~5 사이의 정수로 설정되며, Null 값을 허용한다.
     */
    private Integer rating;

    /**
     * 리뷰에 대한 상세 코멘트.
     * 텍스트 형태로 저장되며, Null 값을 허용한다.
     */
    private String comment;

    /**
     * 리뷰 작성 시간.
     * 리뷰가 데이터베이스에 처음 저장될 때 자동으로 설정되며, 이후 수정할 수 없다.
     */
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    /**
     * 엔티티가 데이터베이스에 persist되기 전에 호출되는 메서드.
     * `createdAt` 필드를 현재 시간으로 설정한다.
     * JPA의 `@PrePersist` 어노테이션을 사용하여 자동으로 호출된다.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }
}
