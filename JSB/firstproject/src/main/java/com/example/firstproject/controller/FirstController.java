package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi") //Url을 요청받는 방식은 Get과 Post 방식이 있는데 이 어노테이션은 Get 방식을 처리한다.
    public String niceToMeetYou(Model model) {
        //Controller가 처리한 데이터를 View로 전달
        model.addAttribute("username", "홍팍");
        return "greetings"; //greetings라는 뷰를 찾아서 리턴한다.
    }

    @GetMapping("/bye")
    public String niceToMeetYou2(Model model) {
        model.addAttribute("username", "홍팍");
        return "goodbye";
    }
}
