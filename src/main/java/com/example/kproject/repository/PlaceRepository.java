package com.example.kproject.repository;

import com.example.kproject.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * PlaceRepository는 Place 엔티티에 대한 데이터베이스 액세스를 처리한다.
 * JpaRepository를 확장하여 기본 CRUD 기능과 추가적인 사용자 정의 쿼리를 제공한다.
 */
public interface PlaceRepository extends JpaRepository<Place, Integer> {

    /**
     * 주소, 위도, 경도를 기준으로 Place 엔티티를 조회한다.
     * 주소(Address), 위도(Latitude), 경도(Longitude)가 모두 일치하는 데이터베이스의 Place를 검색한다.
     *
     * @param address   Place의 주소 (주소는 데이터베이스에 저장된 값과 일치해야 함)
     * @param latitude  Place의 위도 (소수점 이하 8자리까지 정확도 요구)
     * @param longitude Place의 경도 (소수점 이하 8자리까지 정확도 요구)
     * @return 주소, 위도, 경도가 일치하는 Place 객체를 Optional로 반환.
     *         데이터베이스에 존재하지 않는 경우 Optional.empty()를 반환.
     */
    Optional<Place> findByAddressAndLatitudeAndLongitude(String address, Double latitude, Double longitude);
}
