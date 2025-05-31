package com.example.kproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 애플리케이션의 전역 설정을 관리하는 클래스.
 * Kakao API 키와 같은 중요 설정 값을 관리하고 Bean으로 등록한다.
 */
@Configuration
public class AppConfig {

    /**
     * Kakao JavaScript API 키를 properties 파일에서 읽어오는 필드.
     */
    @Value("${kakao.js.key}")
    private String kakaoJsKey;

    /**
     * Kakao REST API 키를 properties 파일에서 읽어오는 필드.
     */
    @Value("${kakao.rest.key}")
    private String kakaoRestKey;

    /**
     * Kakao JavaScript API 키를 Bean으로 등록.
     * 다른 클래스에서 주입받아 사용할 수 있다.
     *
     * @return Kakao JavaScript API 키
     */
    @Bean
    public String kakaoJsKey() {
        return kakaoJsKey;
    }

    /**
     * Kakao REST API 키를 Bean으로 등록.
     * 서버에서 REST API 호출이 필요한 클래스에서 주입받아 사용할 수 있다.
     *
     * @return Kakao REST API 키
     */
    @Bean
    public String kakaoRestKey() {
        return kakaoRestKey;
    }
}
