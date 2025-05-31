package com.example.firstproject.Firstcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //어노테이션이 없다면 컨트롤러로 인식하지 못한다.
public class FirstController {

   @GetMapping("/halo") //get방식으로 받아온다.(URL이 /hi)
    public String niceToMeet() {
        //hi로 이동하면 자동으로 실행되는 클래스
        //hi라는 매핑으로 Controller가 처리한 데이터로 greetings라는 view로 Post한다.
        //greetings라는 템플릿으로 이동(처리한 데이터와 함께)
        return "greetings2";
    }

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        //model을 통해서 파라미터로써 view로 전달
        model.addAttribute("username", "아이유");
        System.out.println("안녕하세요");
        return "greetings";
    }

    @GetMapping("/bye")
    public String bye(Model model) {
        model.addAttribute("username", "조정석");
        return "bye";
    }
}
