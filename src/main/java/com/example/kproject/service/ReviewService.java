package com.example.kproject.service;

import com.example.kproject.entity.Review;
import com.example.kproject.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByPlaceId(Integer placeId) {
        return reviewRepository.findByPlacePlaceId(placeId);
    }
}
