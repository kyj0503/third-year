package com.example.kproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Place {

    /**
     * 장소의 고유 ID.
     * 데이터베이스에서 자동으로 생성되는 기본 키이다.
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
     * 선택적 필드로 Null 값을 허용한다.
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
     * 데이터베이스에 처음 저장될 때 자동으로 설정되며, 이후 수정할 수 없다.
     */
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    /**
     * 엔티티가 데이터베이스에 저장되기 전에 호출된다.
     * `createdAt` 필드를 현재 시간으로 설정한다.
     */
    @PrePersist
    public void prePersist() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }
}
