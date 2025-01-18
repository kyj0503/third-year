package com.webproject.chonstay_backend.providing;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.webproject.chonstay_backend.homeProviding.HomeProviding;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Providing {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long providingId;

    @Column(nullable = false)
    private String providingBody;

    @OneToMany(mappedBy = "providing", fetch = FetchType.LAZY)
    private List<HomeProviding> homeProvidings = new ArrayList<>();
}
