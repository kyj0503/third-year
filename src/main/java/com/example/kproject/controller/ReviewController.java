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

/**
 * ReviewController는 리뷰 생성, 조회, 저장과 관련된 모든 요청을 처리한다.
 * 사용자의 로그인 상태를 확인하고 장소 데이터를 기반으로 리뷰를 작성할 수 있는 페이지를 제공하거나,
 * 작성된 리뷰를 데이터베이스에 저장하는 기능을 제공한다.
 */
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
     * 장소 데이터를 받아 데이터베이스에 저장하고, 리뷰 작성 페이지의 URL을 반환한다.
     *
     * @param placeData 장소 데이터 (name, address, latitude, longitude) JSON 형식으로 전달
     * @param session 현재 사용자 세션
     * @return 장소 저장 완료 후 리뷰 작성 페이지로 리다이렉트할 URL
     */
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<?> savePlaceAndRedirect(
            @RequestBody Map<String, Object> placeData,
            HttpSession session) {
        // 세션에서 사용자 ID를 확인
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다."); // 인증되지 않은 사용자 응답
        }

        // 요청에서 장소 데이터 추출
        String name = (String) placeData.get("name");
        String address = (String) placeData.get("address");
        Double latitude = Double.parseDouble(placeData.get("latitude").toString());
        Double longitude = Double.parseDouble(placeData.get("longitude").toString());

        // 장소를 데이터베이스에서 찾거나 새로 생성
        Place place = placeService.findOrCreatePlace(name, address, latitude, longitude);

        // 리뷰 작성 페이지로 리다이렉트할 URL 생성
        String redirectUrl = "/reviews/create/" + place.getPlaceId();
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", redirectUrl);

        return ResponseEntity.ok(response); // URL 응답
    }

    /**
     * 리뷰 작성 폼을 반환한다.
     *
     * @param placeId 리뷰를 작성할 장소 ID
     * @param model 뷰로 전달할 데이터
     * @param session 현재 사용자 세션
     * @return 리뷰 작성 폼 뷰 이름 ("reviews/create")
     */
    @GetMapping("/create/{placeId}")
    public String showReviewForm(@PathVariable Integer placeId, Model model, HttpSession session) {
        // 로그인 여부 확인
        Integer userId = (Integer) session.getAttribute("userId");
        boolean isLoggedIn = userId != null;
        model.addAttribute("isLoggedIn", isLoggedIn);

        if (!isLoggedIn) {
            return "redirect:/login"; // 로그인 필요 시 로그인 페이지로 리다이렉트
        }

        // 장소 정보 조회
        Place place = placeService.getPlaceById(placeId);
        if (place == null) {
            throw new IllegalArgumentException("Place not found"); // 장소가 없을 경우 예외 발생
        }

        // 템플릿에 데이터 전달
        model.addAttribute("place", place);
        model.addAttribute("review", new Review());

        return "reviews/create"; // 리뷰 작성 페이지 반환
    }

    /**
     * 작성된 리뷰를 데이터베이스에 저장한다.
     *
     * @param placeId 리뷰를 작성한 장소의 ID
     * @param review 작성된 리뷰 데이터
     * @param session 현재 사용자 세션
     * @return 저장 완료 후 메인 페이지로 리다이렉트
     */
    @PostMapping("/create/{placeId}")
    public String saveReview(
            @PathVariable Integer placeId,
            @ModelAttribute Review review,
            HttpSession session) {
        // 로그인 여부 확인
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login"; // 로그인 필요 시 로그인 페이지로 리다이렉트
        }

        // 사용자와 장소 데이터 조회
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Place place = placeService.getPlaceById(placeId);
        if (place == null) {
            throw new IllegalArgumentException("Place not found"); // 장소가 없을 경우 예외 발생
        }

        // 리뷰 정보 설정
        review.setUser(user);
        review.setPlace(place);

        // 리뷰 데이터베이스에 저장
        reviewService.saveReview(review);

        return "redirect:/"; // 저장 후 메인 페이지로 리다이렉트
    }

    /**
     * 평균 평점순으로 장소 리스트를 정렬하고 표시
     */
    @GetMapping("/list")
    public String listReviewsByAverageRating(Model model) {
        // 평균 평점이 높은 순으로 정렬된 장소와 리뷰 데이터를 가져옴
        var placesWithRatings = reviewService.getPlacesByAverageRating();
        model.addAttribute("placesWithRatings", placesWithRatings);
        return "reviews/list";
    }
}
