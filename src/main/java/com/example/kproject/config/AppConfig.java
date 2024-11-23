package com.example.kproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${kakao.js.key}")
    private String kakaoJsKey;

    @Value("${kakao.rest.key}")
    private String kakaoRestKey;

    @Bean
    public String kakaoJsKey() {
        return kakaoJsKey;
    }

    @Bean
    public String kakaoRestKey() {
        return kakaoRestKey;
    }
}
