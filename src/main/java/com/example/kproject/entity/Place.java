package com.example.kproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Place 엔티티는 장소 정보를 저장하는 데이터베이스 테이블과 매핑된다.
 * 장소의 이름, 주소, 위도, 경도, 설명, 생성 시간을 포함한 정보를 관리한다.
 */
@Entity
@Getter
@Setter
public class Place {

    /**
     * 장소의 고유 ID.
     * 데이터베이스에서 자동으로 생성되는 기본 키 값이다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer placeId;

    /**
     * 장소의 이름.
     * Null 값을 허용하지 않는다.
     */
    @Column(nullable = false)
    private String name;

    /**
     * 장소의 주소.
     * Null 값을 허용하지 않는다.
     */
    @Column(nullable = false)
    private String address;

    /**
     * 장소에 대한 추가 설명.
     * 선택적 필드로, Null 값을 허용한다.
     */
    private String description;

    /**
     * 장소의 위도 값.
     * Null 값을 허용하지 않는다.
     */
    @Column(nullable = false)
    private Double latitude;

    /**
     * 장소의 경도 값.
     * Null 값을 허용하지 않는다.
     */
    @Column(nullable = false)
    private Double longitude;

    /**
     * 장소 생성 시간.
     * Null 값을 허용하지 않으며, 데이터베이스에 처음 저장될 때 자동으로 설정된다.
     * 이후 수정이 불가능하다.
     */
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    /**
     * 엔티티가 데이터베이스에 저장되기 전에 실행되는 메서드.
     * `createdAt` 필드를 현재 시간으로 설정한다.
     * JPA의 `@PrePersist` 어노테이션을 사용하여 자동으로 호출된다.
     */
    @PrePersist
    public void prePersist() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }
}
