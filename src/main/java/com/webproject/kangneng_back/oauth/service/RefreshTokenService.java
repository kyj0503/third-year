package com.webproject.kangneng_back.oauth.service;

import com.webproject.kangneng_back.oauth.entity.RefreshToken;
import com.webproject.kangneng_back.oauth.oauth2.jwt.JWTUtil;
import com.webproject.kangneng_back.oauth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JWTUtil jwtUtil; // JWTUtil 추가

    // Refresh Token 저장
    public RefreshToken saveRefreshToken(String userId, String token, LocalDateTime expiryDate) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUserId(userId);
        refreshToken.setToken(token);
        refreshToken.setExpiryDate(expiryDate);
        return refreshTokenRepository.save(refreshToken);
    }

    // Refresh Token 조회
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    // Refresh Token 삭제
    public void deleteByUserId(String userId) {
        refreshTokenRepository.deleteById(userId);
    }

    // Refresh Token 갱신
    public RefreshToken rotateRefreshToken(String userId, String newToken, LocalDateTime newExpiryDate) {
        RefreshToken refreshToken = refreshTokenRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        refreshToken.setToken(newToken);
        refreshToken.setExpiryDate(newExpiryDate);
        return refreshTokenRepository.save(refreshToken);
    }

    // Access Token 재발급 메서드
    public String refreshAccessToken(String refreshToken) {
        // 1. 리프레시 토큰 조회
        RefreshToken tokenEntity = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token"));

        // 2. 만료 시간 확인
        if (tokenEntity.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Refresh token expired");
        }

        // 3. 사용자 ID 추출
        String userId = jwtUtil.getUsername(refreshToken);

        // 4. 새 Access Token 생성
        return jwtUtil.createAccessToken(userId, "ROLE_USER");
    }
}
