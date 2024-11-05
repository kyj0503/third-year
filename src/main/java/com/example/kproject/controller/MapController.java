package com.example.kproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 컨트롤러에서 API 키를 모델에 추가하여 뷰로 전달
 * 
 * @author 김연재
 */

@Controller
public class MapController {

    private final String kakaoApiKey;

    public MapController(String kakaoApiKey) {
        this.kakaoApiKey = kakaoApiKey;
    }

    @GetMapping("/")
    public String showMap(Model model) {
        model.addAttribute("kakaoApiKey", kakaoApiKey);
        return "map";
    }
}
