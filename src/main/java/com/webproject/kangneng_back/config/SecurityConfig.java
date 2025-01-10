package com.webproject.kangneng_back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // /api/** 경로는 인증 없이 허용
                        .requestMatchers("/h2-console/**").permitAll() // H2 콘솔 접근 허용
                        .anyRequest().authenticated() // 나머지 모든 요청은 인증 필요
                )
                .headers(headers -> headers.frameOptions().disable()) // H2 콘솔의 프레임 옵션 비활성화
                .formLogin(form -> form.disable()) // 폼 기반 인증 비활성화
                .httpBasic(); // 기본 HTTP 인증 허용 (필요 시 제거 가능)

        return http.build();
    }
}
