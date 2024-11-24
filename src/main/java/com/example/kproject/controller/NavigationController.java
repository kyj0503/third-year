package com.example.kproject.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavigationController {

    @Value("${kakao.js.key}")
    private String kakaoJsKey;

    @Value("${kakao.rest.key}")
    private String kakaoRestKey;

    @GetMapping("/navigation")
    public String showNavigationPage(Model model, HttpSession session) {
        // 로그인 여부 확인
        boolean isLoggedIn = session.getAttribute("userId") != null;

        // 모델에 필요한 변수 추가
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("kakaoJsKey", kakaoJsKey);
        model.addAttribute("kakaoRestKey", kakaoRestKey);

        return "navigation"; // navigation.mustache로 이동
    }
}
