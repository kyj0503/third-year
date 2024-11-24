package com.example.kproject.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class MapController {

    private static final Logger logger = LoggerFactory.getLogger(MapController.class);

    @Value("${kakao.js.key}")
    private String kakaoJsKey;

    @Value("${kakao.rest.key}")
    private String kakaoRestKey;

    private static final String DEFAULT_KEYWORD = "카페";

    @GetMapping("/")
    public String showMap(Model model, HttpSession session) {
        logger.info("MapController 호출됨");

        boolean isLoggedIn = session.getAttribute("userId") != null;
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("kakaoJsKey", kakaoJsKey);
        model.addAttribute("kakaoRestKey", kakaoRestKey);
        model.addAttribute("keyword", DEFAULT_KEYWORD);

        return "map";
    }

}
