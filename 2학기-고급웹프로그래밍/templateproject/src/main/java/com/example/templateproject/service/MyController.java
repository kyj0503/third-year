package com.example.templateproject.service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// DispatherServlet 역할
@Controller
public class MyController {

    @GetMapping("/welcome")
    public String welcome(Model model) {
        //Model 객체에 name이라는 변수에 John 데이터값 저장
        model.addAttribute("name", "John");
        return "welcome"; //templates/welcom.mustache 파일을 렌더링
    }

    @RequestMapping("/hi")
    public String hi(Model model) {
        model.addAttribute("username", "이지은");
        return "greetings";
    }

    @RequestMapping("/bye")
    public String bye(Model model) {
        model.addAttribute("nickname", "아이유");
        return "goobbye";
    }

}
