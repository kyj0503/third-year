package com.webproject.chonstay_backend.home;

import static org.springframework.http.HttpStatus.OK;

import com.webproject.chonstay_backend.common.Location;
import com.webproject.chonstay_backend.home.dto.HomeGetResponse;
import com.webproject.chonstay_backend.home.dto.HomesGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/homes")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @PostMapping("/home")
    public ResponseEntity<String> registerHome(@RequestBody HomeRegisterRequestDto requestDto) {
        homeService.registerHome(requestDto); // 서비스 계층에서 등록 처리
        return ResponseEntity.ok("Home successfully registered!");
    }

    @GetMapping("/{homeId}")
    public ResponseEntity<HomeGetResponse> getHome(@PathVariable Long homeId) {

        return ResponseEntity
                .status(OK)
                .body(homeService.getHome(homeId));
    }

    @GetMapping
    public ResponseEntity<HomesGetResponse> getHomes(
            @RequestParam(required = false) String searchQuery,
            @RequestParam(required = false) Location location
    ) {
        return ResponseEntity
                .status(OK)
                .body(homeService.getResultHomes(searchQuery, location));
    }
}
