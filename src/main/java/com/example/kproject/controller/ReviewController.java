package com.example.kproject.controller;

import com.example.kproject.entity.Place;
import com.example.kproject.entity.Review;
import com.example.kproject.entity.User;
import com.example.kproject.service.PlaceService;
import com.example.kproject.service.ReviewService;
import com.example.kproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private UserService userService;

    /**
     * 장소 데이터를 받아 저장하고 리뷰 작성 페이지로 이동 URL 반환
     */
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<?> savePlaceAndRedirect(
            @RequestBody Map<String, Object> placeData,
            HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }

        String name = (String) placeData.get("name");
        String address = (String) placeData.get("address");
        Double latitude = Double.parseDouble(placeData.get("latitude").toString());
        Double longitude = Double.parseDouble(placeData.get("longitude").toString());

        Place place = placeService.findOrCreatePlace(name, address, latitude, longitude);

        // Redirect URL 생성
        String redirectUrl = "/reviews/create/" + place.getPlaceId();
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", redirectUrl);

        return ResponseEntity.ok(response);
    }

    /**
     * 리뷰 작성 폼 표시
     */
    @GetMapping("/create/{placeId}")
    public String showReviewForm(@PathVariable Integer placeId, Model model, HttpSession session) {
        // 세션에서 userId를 가져옴
        Integer userId = (Integer) session.getAttribute("userId");
        boolean isLoggedIn = userId != null;
        model.addAttribute("isLoggedIn", isLoggedIn);

        if (!isLoggedIn) {
            return "redirect:/login";
        }

        // Place 정보를 가져와 템플릿에 전달
        Place place = placeService.getPlaceById(placeId);
        if (place == null) {
            throw new IllegalArgumentException("Place not found");
        }

        model.addAttribute("place", place);
        model.addAttribute("review", new Review());
        return "reviews/create";
    }

    /**
     * 작성된 리뷰 저장
     */
    @PostMapping("/create/{placeId}")
    public String saveReview(
            @PathVariable Integer placeId,
            @ModelAttribute Review review,
            HttpSession session) {
        // 세션에서 userId 확인
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        // User와 Place 확인
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Place place = placeService.getPlaceById(placeId);
        if (place == null) {
            throw new IllegalArgumentException("Place not found");
        }

        // 리뷰 정보 설정 및 저장
        review.setUser(user);
        review.setPlace(place);
        reviewService.saveReview(review);

        return "redirect:/";
    }
}
