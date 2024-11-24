package com.example.kproject.service;

import com.example.kproject.entity.Place;
import com.example.kproject.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * PlaceService는 Place 엔티티와 관련된 비즈니스 로직을 처리한다.
 * PlaceRepository를 사용하여 데이터베이스와 상호작용한다.
 */
@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    /**
     * 데이터베이스에서 모든 장소(Place) 정보를 조회한다.
     *
     * @return 데이터베이스에 저장된 모든 Place 객체의 리스트.
     */
    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    /**
     * 주어진 ID에 해당하는 장소(Place)를 조회한다.
     *
     * @param id 조회할 장소의 고유 식별자.
     * @return ID에 해당하는 Place 객체. 존재하지 않을 경우 null 반환.
     */
    public Place getPlaceById(Integer id) {
        return placeRepository.findById(id).orElse(null);
    }

    /**
     * 새로운 장소(Place)를 저장하거나, 기존의 장소 정보를 갱신한다.
     *
     * @param place 저장 또는 갱신할 Place 객체.
     * @return 저장된 Place 객체.
     */
    public Place savePlace(Place place) {
        return placeRepository.save(place);
    }

    /**
     * 주어진 ID에 해당하는 장소(Place)를 데이터베이스에서 삭제한다.
     *
     * @param id 삭제할 장소의 고유 식별자.
     */
    public void deletePlace(Integer id) {
        placeRepository.deleteById(id);
    }

    /**
     * 주어진 장소 정보를 기반으로 데이터베이스에서 조회하거나,
     * 존재하지 않을 경우 새로 생성하여 저장한다.
     *
     * - 장소의 주소(Address), 위도(Latitude), 경도(Longitude)를 기준으로 장소를 식별한다.
     * - 존재하지 않을 경우, 새로운 Place 객체를 생성하여 저장한다.
     *
     * @param name      장소 이름.
     * @param address   장소 주소.
     * @param latitude  장소의 위도.
     * @param longitude 장소의 경도.
     * @return 데이터베이스에 저장된 Place 객체.
     */
    public Place findOrCreatePlace(String name, String address, Double latitude, Double longitude) {
        return placeRepository.findByAddressAndLatitudeAndLongitude(address, latitude, longitude)
                .orElseGet(() -> {
                    // 장소가 존재하지 않으면 새로운 Place 객체 생성
                    Place place = new Place();
                    place.setName(name);
                    place.setAddress(address);
                    place.setLatitude(latitude);
                    place.setLongitude(longitude);

                    // PrePersist로 createdAt 자동 설정
                    return placeRepository.save(place);
                });
    }
}
