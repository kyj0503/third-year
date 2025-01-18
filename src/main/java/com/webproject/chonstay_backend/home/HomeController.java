package com.webproject.chonstay_backend.home;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @PostMapping("/home")
    public ResponseEntity<String> registerHome(@RequestBody HomeRegisterRequestDto requestDto) {
        homeService.registerHome(requestDto); // 서비스 계층에서 등록 처리
        return ResponseEntity.ok("Home successfully registered!");
    }


}
