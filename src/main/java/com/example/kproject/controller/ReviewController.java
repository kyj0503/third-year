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
     * 장소 데이터를 저장하고 리뷰 작성 페이지 URL을 반환하는 메서드.
     *
     * @param placeData 장소 데이터 (name, address, latitude, longitude)
     * @param session 현재 사용자 세션
     * @return 장소 저장 후 리다이렉트할 URL 응답
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

        String redirectUrl = "/reviews/create/" + place.getPlaceId();
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", redirectUrl);

        return ResponseEntity.ok(response);
    }

    /**
     * 리뷰 작성 폼을 반환하는 메서드.
     *
     * @param placeId 리뷰 작성 대상 장소 ID
     * @param model 뷰에 데이터를 전달하기 위한 객체
     * @param session 현재 사용자 세션
     * @return 리뷰 작성 페이지 뷰 이름 ("reviews/create")
     */
    @GetMapping("/create/{placeId}")
    public String showReviewForm(@PathVariable Integer placeId, Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        boolean isLoggedIn = userId != null;
        model.addAttribute("isLoggedIn", isLoggedIn);

        if (!isLoggedIn) {
            return "redirect:/login";
        }

        Place place = placeService.getPlaceById(placeId);
        if (place == null) {
            throw new IllegalArgumentException("Place not found");
        }

        model.addAttribute("place", place);
        model.addAttribute("review", new Review());

        return "reviews/create";
    }

    /**
     * 작성된 리뷰를 데이터베이스에 저장하는 메서드.
     *
     * @param placeId 리뷰 대상 장소 ID
     * @param review 작성된 리뷰 데이터
     * @param session 현재 사용자 세션
     * @return 저장 완료 후 메인 페이지로 리다이렉트
     */
    @PostMapping("/create/{placeId}")
    public String saveReview(
            @PathVariable Integer placeId,
            @ModelAttribute Review review,
            HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        User user = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Place place = placeService.getPlaceById(placeId);
        if (place == null) {
            throw new IllegalArgumentException("Place not found");
        }

        review.setUser(user);
        review.setPlace(place);

        reviewService.saveReview(review);

        return "redirect:/";
    }

    /**
     * 평균 평점순으로 정렬된 장소 리스트를 표시하는 메서드.
     *
     * @param model 뷰에 데이터를 전달하기 위한 객체
     * @return 리뷰 리스트 페이지 뷰 이름 ("reviews/list")
     */
    @GetMapping("/list")
    public String listReviewsByAverageRating(Model model) {
        var placesWithRatings = reviewService.getPlacesByAverageRating();
        model.addAttribute("placesWithRatings", placesWithRatings);
        return "reviews/list";
    }
}
