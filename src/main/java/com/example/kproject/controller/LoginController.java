package com.example.kproject.controller;

import com.example.kproject.entity.User;
import com.example.kproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // 로그인 페이지 표시
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        return "login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model, HttpSession session) {
        User user = userService.authenticateUser(username, password);

        if (user == null) {
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다!");
            return "login";
        }

        // 로그인 성공 시 세션에 사용자 정보 저장
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("username", user.getUsername());

        // 확인용 로그
        System.out.println("로그인 성공: " + user.getUsername() + " (ID: " + user.getUserId() + ")");
        return "redirect:/";
    }


    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/"; // 로그아웃 후 메인 페이지로 리다이렉트
    }
}
