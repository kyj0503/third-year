package com.webproject.chonstay_backend.homeProviding;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.webproject.chonstay_backend.home.Home;
import com.webproject.chonstay_backend.providing.Providing;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HomeProviding {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long homeProvidingId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id", nullable = false)
    private Home home;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "providing_id", nullable = false)
    private Providing providing;
}
