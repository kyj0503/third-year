package com.webproject.kangneng_back.controller;

import com.webproject.kangneng_back.jwt.JWTUtil;
import com.webproject.kangneng_back.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RefreshTokenService refreshTokenService;
    private final JWTUtil jwtUtil;

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        try {
            if (!token.startsWith("Bearer ")) {
                return ResponseEntity.badRequest().body("Invalid token format");
            }

            String accessToken = token.substring(7);
            String username = jwtUtil.getUsername(accessToken);

            // RefreshToken DB에서 삭제
            refreshTokenService.deleteByUserId(username);

            return ResponseEntity.ok()
                    .body("Successfully logged out");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Logout failed: " + e.getMessage());
        }
    }
}