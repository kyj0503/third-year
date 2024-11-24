package com.example.kproject.controller;

import com.example.kproject.entity.User;
import com.example.kproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

/**
 * LoginController는 사용자 인증과 관련된 요청을 처리하는 컨트롤러다.
 * 사용자가 로그인하거나 로그아웃하는 요청을 처리하며, 로그인 상태를 세션을 통해 관리한다.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 로그인 페이지를 표시한다.
     *
     * @param model 모델 객체를 통해 뷰에 데이터를 전달할 수 있다.
     * @return 로그인 페이지의 이름 ("login")
     */
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        return "login";
    }

    /**
     * 사용자의 로그인 요청을 처리한다.
     * 사용자 이름과 비밀번호를 받아 인증하고, 성공 시 세션에 사용자 정보를 저장한다.
     *
     * @param username 사용자가 입력한 아이디
     * @param password 사용자가 입력한 비밀번호
     * @param model 로그인 실패 시 오류 메시지를 전달하기 위한 모델 객체
     * @param session 사용자 세션 객체로, 로그인 성공 시 사용자 정보를 저장한다.
     * @return 로그인 성공 시 메인 페이지로 리다이렉트, 실패 시 로그인 페이지를 다시 반환
     */
    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model, HttpSession session) {
        // 사용자 인증
        User user = userService.authenticateUser(username, password);

        if (user == null) {
            // 인증 실패 시 오류 메시지 추가
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다!");
            return "login";
        }

        // 인증 성공 시 사용자 정보를 세션에 저장
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("username", user.getUsername());

        // 인증 성공 로그 출력
        System.out.println("로그인 성공: " + user.getUsername() + " (ID: " + user.getUserId() + ")");
        return "redirect:/"; // 메인 페이지로 리다이렉트
    }

    /**
     * 사용자의 로그아웃 요청을 처리한다.
     * 세션을 무효화하고 메인 페이지로 리다이렉트한다.
     *
     * @param session 사용자 세션 객체로, 로그아웃 시 세션 정보를 초기화한다.
     * @return 메인 페이지로 리다이렉트
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션 무효화
        session.invalidate();
        return "redirect:/"; // 로그아웃 후 메인 페이지로 리다이렉트
    }
}
