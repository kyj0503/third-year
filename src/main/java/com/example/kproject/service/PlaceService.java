package com.example.kproject.service;

import com.example.kproject.entity.Place;
import com.example.kproject.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public Place getPlaceById(Integer id) {
        return placeRepository.findById(id).orElse(null);
    }

    public Place savePlace(Place place) {
        return placeRepository.save(place);
    }

    public void deletePlace(Integer id) {
        placeRepository.deleteById(id);
    }


    public Place findOrCreatePlace(String name, String address, Double latitude, Double longitude) {
        return placeRepository.findByAddressAndLatitudeAndLongitude(address, latitude, longitude)
                .orElseGet(() -> {
                    Place place = new Place();
                    place.setName(name);
                    place.setAddress(address);
                    place.setLatitude(latitude);
                    place.setLongitude(longitude);
                    return placeRepository.save(place); // createdAt은 @PrePersist로 설정됨
                });
    }
}
