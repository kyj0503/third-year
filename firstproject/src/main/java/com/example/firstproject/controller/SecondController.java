package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Controller
public class SecondController {

    // 생성자
    public SecondController() {
        System.out.println("Hello Second Controller");
    }

    @GetMapping("/main")
    @ResponseBody
    /*
    컨트롤러 메서드가 반환하는 데이터를 직접 HTTP 응답 바디에 쓰도록 명령
    뷰를 연동하지 않을 때 사용
    JSON, XML, 또는 단순한 텍스트 데이터를 클라이언트에 반환할 때 사용
     */
    public String main1() {
        return "<h1>Hello ResponseBody</h1>";
    }

    @GetMapping("/now")
    @ResponseBody
    public String now() {
        LocalDate now = LocalDate.now();

        String result = String.format("<h1>오늘은 %s 입니다.</h1>", now);
        return result;
    }

    int cnt = 123;

    @GetMapping("/count")
    @ResponseBody
    public String count() {
        cnt++;
        String result = String.format("<h1>오늘의 방문자 수는 %s 입니다.</h1>", cnt);
        return result;
    }

    @GetMapping("/count2")
    @ResponseBody
    public String count2() {
        cnt++;

        String html = "";
        String cntStr = Integer.toString(cnt);

        for(int i=0; i < cntStr.length(); i++)
            html += String.format("<img src='img/number/%c.png' width='100' height='100'>", cntStr.charAt(i));
        return html;
    }
}
