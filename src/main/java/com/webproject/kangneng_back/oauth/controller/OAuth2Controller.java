package com.webproject.kangneng_back.oauth.controller;

import com.webproject.kangneng_back.oauth.oauth2.jwt.JWTUtil;
import com.webproject.kangneng_back.oauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth2")
public class OAuth2Controller {

    private final UserService userService;
    private final JWTUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> processSignup(
            @RequestHeader("Authorization") String token,
            @RequestBody String nickname) {

        if (!token.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token format");
        }

        String jwtToken = token.substring(7);

        try {
            String username = jwtUtil.getUsername(jwtToken);
            userService.updateNickname(username, nickname);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("회원가입 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}