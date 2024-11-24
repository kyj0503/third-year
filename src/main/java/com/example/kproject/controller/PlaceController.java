package com.example.kproject.controller;

import com.example.kproject.entity.Place;
import com.example.kproject.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    // 모든 Place 데이터를 반환
    @GetMapping
    public List<Place> getAllPlaces() {
        return placeService.getAllPlaces();
    }
}
