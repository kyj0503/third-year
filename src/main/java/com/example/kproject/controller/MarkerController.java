package com.example.kproject.controller;

import com.example.kproject.entity.MarkerEntity;
import com.example.kproject.service.MarkerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MarkerController {

    private final MarkerService markerService;

    public MarkerController(MarkerService markerService) {
        this.markerService = markerService;
    }

    @GetMapping("/markers")
    public List<MarkerEntity> getMarkers() {
        return markerService.getAllMarkers();
    }
}