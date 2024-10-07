package com.example.templateproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @GetMapping("/welcome")
    public String welcome(Model model) {

        model.addAttribute("name", "John");
        return "welcome";
    }

    @RequestMapping("/hi")
    public String hi(Model model) {
        model.addAttribute("name", "이지은");
        return "greeting";
    }

    @GetMapping("/objectex")
    public String person(Model model) {

        Person person1 = new Person("아이유", 25, "서울특별시");
        Person person2 = new Person("이세영", 25, "광주");

        model.addAttribute("person", person1);
        model.addAttribute("person2", person2);
        return "objectex";
    }

    @GetMapping("/show-inupt")
    public String showInupt(Model model) {

    }
}
