package com.example.boardproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstApiController {

    //hello world 출력
    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World!!";
    }
}
