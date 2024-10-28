package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ThirdController {

    //hello username
    @GetMapping("/users/{username}")
    @ResponseBody
    public String getUser(@PathVariable String username) {

        return "hello " + username + "!";

    }

    @GetMapping("/sum/{number}")
    @ResponseBody
    public String sum(@PathVariable int number) {
        long sum = 0;
        for (int i = 0; i < number; i++) {
            sum += (i+1);
        }

        String html = "<h1>1에서 " + number + "까지의 수의 합은 " + sum + "입니다.</h1>";
        return html;
    }

    //users/kim/orders/500
    @GetMapping("/users/{username}/orders/{orderNo}")
    @ResponseBody
    public String getOrder(@PathVariable String username, @PathVariable int orderNo) {
        return "User Name: " + username + ", Order No: " + orderNo;
    }

    //Category: technology, Post ID: 123
    //post/technology/123
    @GetMapping("/posts/{category}/{postId}")
    @ResponseBody
    public String getPostDetails(@PathVariable String category, @PathVariable int postId) {
        return "Category: " + category + ", Post Id: " + postId;
    }

}
