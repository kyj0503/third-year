package com.example.kproject.service;

import com.example.kproject.entity.Place;
import com.example.kproject.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    /**
     * 데이터베이스에서 모든 장소 정보를 조회하는 메서드.
     *
     * @return 저장된 모든 Place 객체 리스트
     */
    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    /**
     * 특정 ID에 해당하는 장소를 조회하는 메서드.
     *
     * @param id 조회할 장소의 ID
     * @return ID에 해당하는 Place 객체. 존재하지 않을 경우 null 반환
     */
    public Place getPlaceById(Integer id) {
        return placeRepository.findById(id).orElse(null);
    }

    /**
     * 새로운 장소를 저장하거나 기존 장소 정보를 갱신하는 메서드.
     *
     * @param place 저장 또는 갱신할 Place 객체
     * @return 저장된 Place 객체
     */
    public Place savePlace(Place place) {
        return placeRepository.save(place);
    }

    /**
     * 특정 ID에 해당하는 장소를 데이터베이스에서 삭제하는 메서드.
     *
     * @param id 삭제할 장소의 ID
     */
    public void deletePlace(Integer id) {
        placeRepository.deleteById(id);
    }

    /**
     * 주어진 장소 정보를 기반으로 장소를 조회하거나 새로 생성하여 저장하는 메서드.
     *
     * @param name      장소 이름
     * @param address   장소 주소
     * @param latitude  장소의 위도
     * @param longitude 장소의 경도
     * @return 데이터베이스에 저장된 Place 객체
     */
    public Place findOrCreatePlace(String name, String address, Double latitude, Double longitude) {
        return placeRepository.findByAddressAndLatitudeAndLongitude(address, latitude, longitude)
                .orElseGet(() -> {
                    Place place = new Place();
                    place.setName(name);
                    place.setAddress(address);
                    place.setLatitude(latitude);
                    place.setLongitude(longitude);
                    return placeRepository.save(place);
                });
    }
}
