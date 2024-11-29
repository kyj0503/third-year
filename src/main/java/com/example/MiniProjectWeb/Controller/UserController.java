package com.example.MiniProjectWeb.Controller;

import com.example.MiniProjectWeb.Entity.Post;
import com.example.MiniProjectWeb.Entity.User;
import com.example.MiniProjectWeb.Repository.PostRepository;
import com.example.MiniProjectWeb.Repository.UserRepository;
import com.example.MiniProjectWeb.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 사용자 관련 요청을 처리하는 컨트롤러 클래스.
 * 로그인, 로그아웃, 회원가입 및 메인 페이지 표시 기능 제공.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    /**
     * 메인 화면 표시.
     * 게시물 목록과 사용자 정보를 모델에 추가하여 뷰에 전달.
     * @param session 현재 사용자 세션.
     * @param model 뷰로 데이터를 전달하는 객체.
     * @return 메인 화면 템플릿 이름.
     */
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        // 세션에서 로그인된 사용자 정보 가져오기
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user); // 사용자 정보를 모델에 추가
        }

        // 세션에 저장된 에러 메시지를 모델에 추가
        String error = (String) session.getAttribute("error");
        if (error != null) {
            model.addAttribute("error", error); // 에러 메시지 전달
            session.removeAttribute("error"); // 메시지를 세션에서 제거
        }

        // 게시물 목록을 모델에 추가
        model.addAttribute("posts", postRepository.findAll());
        return "index"; // 메인 화면 템플릿 반환
    }

    /**
     * 로그인 페이지를 반환.
     * @return 로그인 페이지 템플릿 이름.
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.mustache 템플릿 반환
    }

    /**
     * 로그인 요청 처리.
     * 사용자 정보를 검증하고 세션에 사용자 정보를 저장.
     * @param username 사용자 이름.
     * @param password 사용자 비밀번호.
     * @param session 현재 사용자 세션.
     * @return 로그인 성공 시 게시물 페이지, 실패 시 로그인 페이지로 리다이렉트.
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        // 사용자 이름으로 사용자 정보 조회
        User user = userRepository.findByUsername(username);
        if (user != null && userService.validatePassword(password, user.getPassword())) {
            // 비밀번호 검증 성공 시 세션에 사용자 정보 저장
            session.setAttribute("user", user);
            return "redirect:/posts"; // 게시물 페이지로 리다이렉트
        }

        // 로그인 실패 시 에러 메시지 설정
        session.setAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
        return "redirect:/login"; // 로그인 페이지로 리다이렉트
    }

    /**
     * 로그아웃 요청 처리.
     * 세션을 무효화하여 로그아웃 수행.
     * @param session 현재 사용자 세션.
     * @return 메인 화면으로 리다이렉트.
     */
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/"; // 메인 화면으로 리다이렉트
    }

    /**
     * 회원가입 페이지를 반환.
     * @return 회원가입 페이지 템플릿 이름.
     */
    @GetMapping("/register")
    public String registerPage() {
        return "register"; // register.mustache 템플릿 반환
    }

    /**
     * 회원가입 요청 처리.
     * 새 사용자 정보를 저장하고 세션에 사용자 정보를 추가.
     * @param username 사용자 이름.
     * @param password 사용자 비밀번호.
     * @param session 현재 사용자 세션.
     * @return 회원가입 성공 시 메인 화면으로 리다이렉트.
     */
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, HttpSession session) {
        // 사용자 등록
        boolean success = userService.registerUser(username, password);

        if (!success) {
            // 사용자 이름 중복 시 에러 메시지 설정
            session.setAttribute("error", "이미 존재하는 사용자 이름입니다.");
            return "redirect:/register"; // 회원가입 페이지로 리다이렉트
        }

        // 새로 등록된 사용자 정보 조회
        User user = userRepository.findByUsername(username);
        session.setAttribute("user", user); // 세션에 사용자 정보 저장
        session.setAttribute("success", "회원가입이 완료되었습니다! 로그인하세요."); // 성공 메시지 추가

        return "redirect:/"; // 메인 화면으로 리다이렉트
    }
}
