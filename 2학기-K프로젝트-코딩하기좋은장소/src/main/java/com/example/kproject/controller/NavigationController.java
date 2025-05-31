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

    /**
     * 네비게이션 페이지를 표시하는 메서드.
     * 로그인 여부와 Kakao API 키를 템플릿에 전달한다.
     *
     * @param model 템플릿에 데이터를 전달하기 위한 Model 객체
     * @param session 현재 사용자 세션 객체
     * @return 네비게이션 페이지 뷰 이름 ("navigation")
     */
    @GetMapping("/navigation")
    public String showNavigationPage(Model model, HttpSession session) {
        boolean isLoggedIn = session.getAttribute("userId") != null;

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("kakaoJsKey", kakaoJsKey);
        model.addAttribute("kakaoRestKey", kakaoRestKey);

        return "navigation";
    }
}
