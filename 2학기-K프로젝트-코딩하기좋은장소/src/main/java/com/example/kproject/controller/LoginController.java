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

    /**
     * 로그인 페이지를 표시하는 메서드.
     *
     * @param model 뷰에 데이터를 전달하기 위한 모델 객체
     * @return 로그인 페이지 뷰 이름 ("login")
     */
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        return "login";
    }

    /**
     * 사용자의 로그인 요청을 처리하는 메서드.
     * 사용자 이름과 비밀번호를 인증하고 성공 시 세션에 사용자 정보를 저장한다.
     *
     * @param username 사용자가 입력한 아이디
     * @param password 사용자가 입력한 비밀번호
     * @param model 인증 실패 시 오류 메시지를 전달할 모델 객체
     * @param session 로그인 성공 시 사용자 정보를 저장할 세션 객체
     * @return 로그인 성공 시 메인 페이지로 리다이렉트, 실패 시 로그인 페이지 반환
     */
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

        session.setAttribute("userId", user.getUserId());
        session.setAttribute("username", user.getUsername());

        System.out.println("로그인 성공: " + user.getUsername() + " (ID: " + user.getUserId() + ")");
        return "redirect:/";
    }

    /**
     * 사용자의 로그아웃 요청을 처리하는 메서드.
     * 세션을 무효화한 뒤 메인 페이지로 리다이렉트한다.
     *
     * @param session 세션 객체
     * @return 메인 페이지로 리다이렉트
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
