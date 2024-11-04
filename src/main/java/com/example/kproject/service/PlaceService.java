package com.example.kproject.service;

import com.example.kproject.entity.Place;
import com.example.kproject.repository.PlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public Place createPlace(Place place) {
        return placeRepository.save(place);
    }

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }
}
