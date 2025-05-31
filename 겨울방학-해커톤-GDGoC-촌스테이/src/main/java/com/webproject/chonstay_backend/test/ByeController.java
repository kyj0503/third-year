package com.webproject.chonstay_backend.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ByeController {

    @GetMapping("/bye")
    public String sayGoodbye() {
        return "Good Bye";
    }
}
