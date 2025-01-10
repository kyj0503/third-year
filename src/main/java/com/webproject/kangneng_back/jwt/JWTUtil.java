package com.webproject.kangneng_back.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private SecretKey secretKey;

    // Refresh Token 만료 시간: 예를 들어 7일
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;

    public JWTUtil(@Value("${spring.jwt.secret}") String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getUsername(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createJwt(String username, String role, Long expiredMs) {
        return Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

    // 새로운 Refresh Token 생성
    public String createRefreshToken(String username) {
        return Jwts.builder()
                .claim("username", username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(secretKey)
                .compact();
    }

    // Refresh Token 검증 후 재발급
    public String rotateRefreshToken(String refreshToken) {
        if (isExpired(refreshToken)) {
            throw new IllegalStateException("Refresh token is expired.");
        }

        String username = getUsername(refreshToken);
        return createRefreshToken(username);
    }

    // Access Token 재발급
    public String reissueAccessToken(String refreshToken) {
        if (isExpired(refreshToken)) {
            throw new IllegalStateException("Refresh token is expired.");
        }

        String username = getUsername(refreshToken);
        String role = getRole(refreshToken); // role 정보를 저장했다면
        return createJwt(username, role, 1000 * 60 * 60L); // 1시간 유효기간 설정
    }



}
