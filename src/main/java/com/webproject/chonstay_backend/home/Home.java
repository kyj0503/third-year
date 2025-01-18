package com.webproject.chonstay_backend.home;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.webproject.chonstay_backend.common.Location;
import com.webproject.chonstay_backend.homeProviding.HomeProviding;
import com.webproject.chonstay_backend.homeTodo.HomeTodo;
import com.webproject.chonstay_backend.liked.Liked;
import com.webproject.chonstay_backend.reservation.Reservation;
import com.webproject.chonstay_backend.review.Review;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Location location;

    @OneToMany(mappedBy = "home", fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "home", fetch = FetchType.LAZY)
    private List<HomeProviding> homeProvidings = new ArrayList<>();

    @OneToMany(mappedBy = "home", fetch = FetchType.LAZY)
    private List<HomeTodo> homeTodos = new ArrayList<>();

    @OneToMany(mappedBy = "home", fetch = FetchType.LAZY)
    private List<Liked> likeds = new ArrayList<>();

    @OneToMany(mappedBy = "home", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();
}
