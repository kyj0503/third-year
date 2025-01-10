package com.webproject.kangneng_back.oauth2;

import com.webproject.kangneng_back.dto.CustomOAuth2User;
import com.webproject.kangneng_back.jwt.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    public CustomSuccessHandler(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        String username = customUserDetails.getUsername();

        String redirectUrl = customUserDetails.isNewUser() ?
                "http://localhost:3000/signup" :
                "http://localhost:3000/main";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String accessToken = jwtUtil.createAccessToken(username, role);
        String refreshToken = jwtUtil.createRefreshToken(username);


        System.out.println("\n=== 토큰 및 인증 정보 ===");
        System.out.println("Access Token: " + accessToken);
        System.out.println("Refresh Token: " + refreshToken);
        System.out.println("Is New User: " + customUserDetails.isNewUser());
        System.out.println("Redirect URL: " + redirectUrl);
        System.out.println("====================\n");

        // 헤더에 토큰과 신규 사용자 여부 설정
        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("X-Is-New-User", String.valueOf(customUserDetails.isNewUser()));
        response.setHeader("RefreshToken", refreshToken);

        // 리다이렉트 URL 설정

        response.sendRedirect(redirectUrl);
    }
}