package com.example.kproject.repository;

import com.example.kproject.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Integer> {

    /**
     * 주소, 위도, 경도를 기준으로 Place 엔티티를 조회하는 메서드.
     *
     * @param address   Place의 주소
     * @param latitude  Place의 위도
     * @param longitude Place의 경도
     * @return 주소, 위도, 경도가 일치하는 Place 객체를 Optional로 반환
     */
    Optional<Place> findByAddressAndLatitudeAndLongitude(String address, Double latitude, Double longitude);
}
