package com.example.kproject.controller;

import com.example.kproject.entity.MarkerEntity;
import com.example.kproject.service.MarkerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MapController {

    private final MarkerService markerService;

    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    public MapController(MarkerService markerService) {
        this.markerService = markerService;
    }

    @GetMapping("/")
    public String showMap(Model model) {
        // 모든 마커 데이터를 가져옴
        List<MarkerEntity> markerEntities = markerService.getAllMarkers();

        // ObjectMapper를 사용하여 Java 객체를 JSON으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String markersJson = ""; // JSON 문자열 초기화

        try {
            markersJson = objectMapper.writeValueAsString(markerEntities);
        } catch (JsonProcessingException e) {
            // 예외 처리: 변환에 실패한 경우 로깅하거나 적절히 처리
            e.printStackTrace();
        }

        // Kakao API 키 추가
        model.addAttribute("kakaoApiKey", kakaoApiKey);

        // 템플릿으로 JSON 데이터를 전달
        model.addAttribute("markers", markersJson);

        return "map"; // Mustache 템플릿 "map"으로 이동
    }
}




