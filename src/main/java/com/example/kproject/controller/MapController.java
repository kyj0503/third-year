package com.example.kproject.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MapController는 애플리케이션의 메인 페이지(지도)를 표시하는 컨트롤러다.
 * Kakao API 키를 사용해 지도와 관련된 데이터를 클라이언트에 전달하며,
 * 사용자 로그인 상태를 확인하여 로그인 여부에 따른 동작을 처리한다.
 */
@Controller
public class MapController {

    // 로깅을 위한 Logger 객체
    private static final Logger logger = LoggerFactory.getLogger(MapController.class);

    /**
     * Kakao JavaScript API 키.
     * 애플리케이션의 properties 파일에서 값을 읽어와 설정된다.
     */
    @Value("${kakao.js.key}")
    private String kakaoJsKey;

    /**
     * Kakao REST API 키.
     * 애플리케이션의 properties 파일에서 값을 읽어와 설정된다.
     */
    @Value("${kakao.rest.key}")
    private String kakaoRestKey;

    /**
     * 기본 검색 키워드. 초기에는 "카페"로 설정된다.
     */
    private static final String DEFAULT_KEYWORD = "카페";

    /**
     * 메인 페이지(지도)를 표시한다.
     * 로그인 여부를 확인하여 isLoggedIn 값을 템플릿에 전달하며,
     * Kakao API 키와 기본 검색 키워드도 함께 전달한다.
     *
     * @param model 데이터를 템플릿에 전달하기 위한 Model 객체
     * @param session 현재 사용자 세션 객체
     * @return 지도 페이지의 뷰 이름 ("map")
     */
    @GetMapping("/")
    public String showMap(Model model, HttpSession session) {
        // 요청 처리 시작 로그
        logger.info("MapController 호출됨");

        // 세션에서 userId가 있는지 확인하여 로그인 여부를 판단
        boolean isLoggedIn = session.getAttribute("userId") != null;

        // 템플릿에 전달할 데이터 설정
        model.addAttribute("isLoggedIn", isLoggedIn); // 로그인 여부
        model.addAttribute("kakaoJsKey", kakaoJsKey); // Kakao JavaScript API 키
        model.addAttribute("kakaoRestKey", kakaoRestKey); // Kakao REST API 키
        model.addAttribute("keyword", DEFAULT_KEYWORD); // 기본 검색 키워드

        // 지도 페이지를 반환
        return "map";
    }
}
