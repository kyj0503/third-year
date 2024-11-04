package com.example.kproject.service;

import com.example.kproject.entity.Marker;
import com.example.kproject.repository.MarkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkerService {

    private final MarkerRepository markerRepository;

    public MarkerService(MarkerRepository markerRepository) {
        this.markerRepository = markerRepository;
    }

    public List<Marker> getAllMarkers() {
        return markerRepository.findAll();
    }
}

