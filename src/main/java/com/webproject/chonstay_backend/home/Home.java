package com.webproject.chonstay_backend.home;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Home {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long homeId;

    @Column(nullable = false)
    private String homeName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private BigDecimal latitude;

    @Column(nullable = false)
    private BigDecimal longitude;

    @Column(nullable = false)
    private Short maxGuest;

    @Column(columnDefinition = "text", nullable = false)
    private String description;

    @Column(columnDefinition = "text", nullable = true)
    private String homeImageUrl;

    @Column(columnDefinition = "Boolean default false", nullable = false)
    private Boolean homeStatus;

//    @OneToMany(mappedBy = "novel", fetch = FetchType.LAZY)
//    private List<UserNovel> userNovels = new ArrayList<>();
//
//    @OneToMany(mappedBy = "novel", fetch = FetchType.LAZY)
//    private List<NovelGenre> novelGenres = new ArrayList<>();
}
