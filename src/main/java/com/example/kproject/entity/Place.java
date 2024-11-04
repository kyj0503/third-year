package com.example.kproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "opening_hours", length = 100)
    private String openingHours;

    @Column(nullable = false, precision = 18)
    private Double latitude;

    @Column(nullable = false, precision = 18)
    private Double longitude;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // placeId를 인자로 받는 생성자 추가
    public Place(Long placeId) {
        this.placeId = placeId;
    }

    // 기본 생성자 필요 (Lombok으로 생성되지만, 명시적으로 추가 가능)
    public Place() {
    }
}
