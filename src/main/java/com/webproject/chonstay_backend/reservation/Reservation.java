package com.webproject.chonstay_backend.reservation;

import com.webproject.chonstay_backend.home.Home;
import com.webproject.chonstay_backend.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

public class Reservation {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long reservationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id", nullable = false)
    private Home home;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate checkInDate;

    @Column(nullable = false)
    private LocalDate checkoutDate;

    //enum
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private short guestNumber;

}
