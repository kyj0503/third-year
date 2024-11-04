package com.example.kproject.controller;

import com.example.kproject.entity.Review;
import com.example.kproject.request.ReviewRequest;
import com.example.kproject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller  // @RestController 대신 @Controller 사용
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    @ResponseBody  // JSON 응답이 필요한 메서드에는 @ResponseBody 추가
    public Review saveReview(@RequestBody ReviewRequest reviewRequest, Principal principal) {
        String username = (principal != null) ? principal.getName() : "admin";
        return reviewService.saveReview(reviewRequest.getPlaceId(), username, reviewRequest.getRating(), reviewRequest.getComment());
    }

    @GetMapping("/sorted/{placeId}")
    @ResponseBody  // JSON 응답이 필요한 메서드에는 @ResponseBody 추가
    public List<Review> getSortedReviews(@PathVariable Long placeId) {
        return reviewService.getReviewsByPlaceId(placeId);
    }

    @GetMapping("/form")
    public String showReviewForm(Model model) {
        model.addAttribute("review", new Review());
        return "reviewForm";  // 템플릿 이름
    }
}
