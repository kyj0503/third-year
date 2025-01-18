package com.webproject.chonstay_backend.home;

import static org.springframework.http.HttpStatus.OK;

import com.webproject.chonstay_backend.home.dto.HomeGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/homes")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/{homeId}")
    public ResponseEntity<HomeGetResponse> getHome(@PathVariable Long homeId) {

        return ResponseEntity
                .status(OK)
                .body(homeService.getHome(homeId));
    }
}
