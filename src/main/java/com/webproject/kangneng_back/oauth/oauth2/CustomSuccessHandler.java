package com.webproject.kangneng_back.oauth.oauth2;

import com.webproject.kangneng_back.oauth.dto.CustomOAuth2User;
import com.webproject.kangneng_back.oauth.oauth2.jwt.JWTUtil;
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
    private static final String FRONTEND_URL = "http://localhost:5173";

    public CustomSuccessHandler(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        String username = customUserDetails.getUsername();

        String redirectUrl = customUserDetails.isNewUser() ?
                FRONTEND_URL + "/signup" :
                FRONTEND_URL + "/main";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String accessToken = jwtUtil.createAccessToken(username, role);
        String refreshToken = jwtUtil.createRefreshToken(username);

        // CORS 헤더 설정
        response.setHeader("Access-Control-Allow-Origin", FRONTEND_URL);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Expose-Headers", "Authorization, RefreshToken, X-Is-New-User");

        // 토큰 및 사용자 정보 헤더 설정
        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("RefreshToken", refreshToken);  // Bearer 접두어 제거
        response.setHeader("X-Is-New-User", String.valueOf(customUserDetails.isNewUser()));

        System.out.println("\n=== 토큰 및 인증 정보 ===");
        System.out.println("Access Token: " + accessToken);
        System.out.println("Refresh Token: " + refreshToken);
        System.out.println("Is New User: " + customUserDetails.isNewUser());
        System.out.println("Redirect URL: " + redirectUrl);
        System.out.println("====================\n");

        response.sendRedirect(redirectUrl);
    }
}