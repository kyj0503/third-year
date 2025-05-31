package com.example.kproject.controller;

import com.example.kproject.entity.Review;
import com.example.kproject.entity.User;
import com.example.kproject.service.FavoriteService;
import com.example.kproject.service.ReviewService;
import com.example.kproject.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private FavoriteService favoriteService;

    /**
     * 회원가입 폼 페이지를 반환하는 메서드.
     *
     * @param model 뷰로 전달할 데이터
     * @return 회원가입 폼 페이지 뷰 이름 ("users/create")
     */
    @GetMapping("/create")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "users/create";
    }

    /**
     * 회원가입 요청을 처리하는 메서드.
     *
     * @param user 회원가입 폼에서 입력받은 사용자 정보
     * @param model 뷰로 전달할 데이터
     * @return 회원가입 성공 시 로그인 페이지로 리다이렉트, 실패 시 회원가입 폼 페이지로 이동
     */
    @PostMapping("/create")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userService.registerUser(user);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "회원가입 중 오류가 발생했습니다: " + e.getMessage());
            return "users/create";
        }
    }

    /**
     * 마이페이지를 표시하는 메서드.
     *
     * @param model 뷰로 전달할 데이터
     * @param session 현재 사용자 세션
     * @return 마이페이지 뷰 이름 ("users/mypage")
     */
    @GetMapping("/mypage")
    public String showMyPage(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        User user = userService.getUserById(userId).orElse(null);
        List<Review> reviews = reviewService.getReviewsByUserId(userId);

        model.addAttribute("user", user);
        model.addAttribute("reviews", reviews);
        model.addAttribute("favorites", favoriteService.getFavoritesByUserId(userId));
        return "users/mypage";
    }

    /**
     * 회원 정보 수정 요청을 처리하는 메서드.
     *
     * @param user 수정된 사용자 정보
     * @param session 현재 사용자 세션
     * @return 수정 완료 후 마이페이지로 리다이렉트
     */
    @PostMapping("/update")
    public String updateUser(User user, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        User existingUser = userService.getUserById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            userService.saveUser(existingUser);
        }
        return "redirect:/users/mypage";
    }
}
