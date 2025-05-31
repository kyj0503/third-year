package com.webproject.kangneng_back.oauth.controller;

import com.webproject.kangneng_back.oauth.jwt.JWTUtil;
import com.webproject.kangneng_back.oauth.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PostMapping("/refresh-token")
    @ResponseBody
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        try {
            String newAccessToken = refreshTokenService.refreshAccessToken(refreshToken);
            return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        }
    }

}