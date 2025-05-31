package com.example.kproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Favorite {

    /**
     * 즐겨찾기 엔티티의 고유 ID.
     * 자동 생성되며 기본 키로 사용된다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favoriteId;

    /**
     * 즐겨찾기와 연결된 사용자.
     * 다대일 관계로 매핑되며, 외래 키는 "userId"이다.
     */
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    /**
     * 즐겨찾기와 연결된 장소.
     * 다대일 관계로 매핑되며, 외래 키는 "placeId"이다.
     */
    @ManyToOne
    @JoinColumn(name = "placeId", nullable = false)
    private Place place;
}
