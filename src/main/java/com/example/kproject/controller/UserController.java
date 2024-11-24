package com.example.kproject.controller;

import com.example.kproject.entity.User;
import com.example.kproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * UserController는 사용자 관리와 관련된 요청을 처리한다.
 * 회원가입 페이지 표시, 회원가입 요청 처리 등의 작업을 담당한다.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 회원가입 폼 페이지를 반환한다.
     *
     * @param model 뷰로 전달할 데이터
     * @return 회원가입 폼 뷰 이름 ("users/create")
     */
    @GetMapping("/create")
    public String showRegistrationForm(Model model) {
        // 새로운 사용자 객체를 모델에 추가하여 템플릿에서 사용 가능하도록 설정
        model.addAttribute("user", new User());
        return "users/create"; // 회원가입 페이지 반환
    }

    /**
     * 회원가입 요청을 처리한다.
     *
     * @param user 회원가입 폼에서 입력받은 사용자 정보
     * @param model 뷰로 전달할 데이터
     * @return 회원가입 성공 시 로그인 페이지로 리다이렉트, 실패 시 다시 회원가입 폼 페이지로 이동
     */
    @PostMapping("/create")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            // 사용자 등록 서비스 호출
            userService.registerUser(user);

            // 회원가입 성공 시 로그인 페이지로 리다이렉트
            return "redirect:/login";
        } catch (Exception e) {
            // 예외 발생 시 에러 메시지를 모델에 추가하고 회원가입 페이지로 이동
            model.addAttribute("error", "회원가입 중 오류가 발생했습니다: " + e.getMessage());
            return "users/create";
        }
    }
}
