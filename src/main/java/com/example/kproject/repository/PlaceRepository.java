package com.example.kproject.repository;

import com.example.kproject.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    Optional<Place> findByAddressAndLatitudeAndLongitude(String address, Double latitude, Double longitude);
}

