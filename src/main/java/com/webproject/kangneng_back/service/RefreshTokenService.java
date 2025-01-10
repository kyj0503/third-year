package com.webproject.kangneng_back.service;

import com.webproject.kangneng_back.entity.RefreshToken;
import com.webproject.kangneng_back.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

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
}
