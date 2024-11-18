package com.example.firstproject.Firstcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ThirdController1 {
    @GetMapping("/users/{username}") //여기 {username}에 값을 입력하면 ResponseBody로 출력되는구나
    @ResponseBody
    public String getUserInfo(@PathVariable String username) { //PathVariable로 인해서 클래스 안에 있는 username을 변수로서 사용 가능
        return "<h1>Hello, " + username + "!</h1>"; //users는 하나의 메서드, kim은 파라미터
    }

    @GetMapping("/sum/{number}") //오류코드 400일 때 Mapping까진 했지만 int문에 문자형을 넣으면 뜬다.
    @ResponseBody
    public String sum(@PathVariable int number) {
        long sum = 0;
        for (int i = 0; i < number; i++) sum += (i+1);

        String html = "<h1>1에서 " + number + "까지의 수의 합은 " + sum + "입니다</h1>";
        return html;
    }

    @GetMapping("/users/{username}/orders/{orderNo}")
    @ResponseBody
    public String getOrder(@PathVariable String username, @PathVariable int orderNo) {
        return "<h2>User Name:" + username + ", Order No:" + orderNo + "</h2>";
    }

    @GetMapping("/posts/{category}/{postId}")
    @ResponseBody
    public String getPost(@PathVariable String category, @PathVariable int postId) {
        return "<h1>Category:" + category + ", Post Id:" + postId + "</h1>";
    }

}
