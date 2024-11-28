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

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    // 메인 화면
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        // 세션에서 사용자 정보 가져오기
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user); // 로그인된 사용자 정보 추가
        }

        // 세션에서 오류 메시지 가져오기
        String error = (String) session.getAttribute("error");
        if (error != null) {
            model.addAttribute("error", error); // 오류 메시지 추가
            session.removeAttribute("error"); // 오류 메시지 처리 후 세션에서 제거
        }

        // 모든 게시물 목록 추가 (로그아웃 후에도 게시물 표시)
        model.addAttribute("posts", postRepository.findAll());
        return "index"; // 메인 화면
    }

    // 회원가입 페이지 매핑
    @GetMapping("/register")
    public String registerPage() {
        return "register"; // register.html 템플릿 반환
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {
        // 아이디와 비밀번호가 공백인지 확인
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            session.setAttribute("error", "아이디와 비밀번호를 입력하세요.");  // 공백 입력 시 오류 메시지
            return "redirect:/"; // 리다이렉트하여 메인 페이지로 돌아감
        }

        // 아이디와 비밀번호가 일치하는지 확인
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);  // 세션에 사용자 정보 저장
            return "redirect:/";  // 로그인 성공 시 메인 화면으로 리다이렉트
        }

        // 아이디나 비밀번호가 틀리면
        session.setAttribute("error", "아이디와 비밀번호가 틀렸습니다.");  // 로그인 실패 메시지
        return "redirect:/";  // 리다이렉트하여 메인 페이지로 돌아감
    }

    // 로그아웃 처리
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화 (로그아웃 처리)
        return "redirect:/"; // 메인 화면으로 리다이렉트
    }

    // 회원가입 처리 (회원가입 후 자동 로그인 처리)
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           HttpSession session) {
        // 회원가입 시도
        boolean success = userService.registerUser(username, password);

        if (!success) {
            session.setAttribute("error", "이미 존재하는 사용자 이름입니다."); // 중복된 사용자 이름 처리
            return "redirect:/register"; // 회원가입 페이지로 리다이렉트
        }

        // 회원가입 성공 후 자동 로그인 처리
        User user = userRepository.findByUsername(username); // 회원가입한 사용자 조회
        session.setAttribute("user", user);  // 세션에 사용자 정보 저장
        session.setAttribute("success", "회원가입이 완료되었습니다! 로그인하세요."); // 성공 메시지 추가

        return "redirect:/"; // 메인 화면으로 리다이렉트
    }

    // 게시물 작성 처리
    @PostMapping("/post")
    public String post(@RequestParam String content, HttpSession session) {
        // 세션에서 사용자 정보 가져오기
        User user = (User) session.getAttribute("user");

        // 사용자 정보가 없으면 로그인 페이지로 리다이렉트
        if (user == null) {
            return "redirect:/";
        }

        // 새로운 게시물 생성
        Post post = new Post();
        post.setContent(content);
        post.setUser(user); // 현재 로그인된 사용자 설정
        postRepository.save(post); // 데이터베이스에 게시물 저장
        return "redirect:/"; // 메인 화면으로 리다이렉트
    }
}
