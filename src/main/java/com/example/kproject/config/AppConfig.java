package com.example.kproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AppConfig 클래스는 애플리케이션 전역 설정을 관리하는 Spring Configuration 클래스다.
 * Kakao API 키 같은 중요한 설정 값을 관리하며, 이를 Bean으로 등록해 다른 클래스에서 주입받아 사용할 수 있게 한다.
 */
@Configuration
public class AppConfig {

    /**
     * Kakao JavaScript API 키를 애플리케이션의 properties 파일에서 읽어오는 변수.
     * 이 키는 Kakao JavaScript SDK를 사용해서 지도를 렌더링하거나 장소 검색 등을 처리할 때 필요하다.
     */
    @Value("${kakao.js.key}")
    private String kakaoJsKey;

    /**
     * Kakao REST API 키를 애플리케이션의 properties 파일에서 읽어오는 변수.
     * 서버에서 Kakao REST API를 호출할 때 사용된다.
     * 예: 경로 탐색, 장소 검색 등.
     */
    @Value("${kakao.rest.key}")
    private String kakaoRestKey;

    /**
     * Kakao JavaScript API 키를 Bean으로 등록.
     * 다른 클래스에서 JavaScript API 키를 사용할 때 이 Bean을 주입받아 사용할 수 있다.
     *
     * @return Kakao JavaScript API 키
     */
    @Bean
    public String kakaoJsKey() {
        return kakaoJsKey;
    }

    /**
     * Kakao REST API 키를 Bean으로 등록.
     * 서버에서 Kakao REST API를 호출할 때 이 Bean을 주입받아 사용할 수 있다.
     *
     * @return Kakao REST API 키
     */
    @Bean
    public String kakaoRestKey() {
        return kakaoRestKey;
    }
}
