package com.example.kproject.controller;

import com.example.kproject.entity.User;
import com.example.kproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 회원가입 페이지 표시
    @GetMapping("/create")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "users/create";
    }

    // 회원가입 처리
    @PostMapping("/create")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userService.registerUser(user);
            return "redirect:/login"; // 회원가입 후 로그인 페이지로 리다이렉트
        } catch (Exception e) {
            model.addAttribute("error", "회원가입 중 오류가 발생했습니다: " + e.getMessage());
            return "users/create";
        }
    }
}
