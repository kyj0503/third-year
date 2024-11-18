package com.example.firstproject.Firstcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.time.LocalDate;

@Controller
public class SecondController {
    public SecondController() { System.out.println("Hello~ Second Controller^^^"); }

    @GetMapping("/main")
    @ResponseBody //view템플릿으로 return하지않고 바로 보여줄 수 있다.
    public String main1() { return "<h1>Hello!</h1>"; }

    @GetMapping("/now")
    @ResponseBody
    public String now() {
        LocalDate now = LocalDate.now();
        return "오늘은 " + now + " 입니다.";
    }

    @GetMapping("/now2")
    @ResponseBody
    public String now2() {
        LocalDate now = LocalDate.now();
        String result = String.format("<h1>오늘은 %s 입니다.</h1>", now);

        return result;
    }

    int cnt = 123;
    @GetMapping("/count")
    @ResponseBody
    public String count() {
        cnt++;
        String result = String.format("오늘의 방문자 수는 %d입니다.", cnt);
        return result;
    }

    @GetMapping("/count2")
    @ResponseBody
    public String count2() {
        cnt++;
        String html ="";
        String cntStr = Integer.toString(cnt); //int -> String
        for (int i=0; i<cntStr.length(); i++) { //반복문으로 진행
            html += String.format("<img src='img/num/%c.png', width='100', height='100'>", cntStr.charAt(i));
        }
        return html;
    }
}
