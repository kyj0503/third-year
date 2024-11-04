package com.example.kproject.service;

import com.example.kproject.entity.Place;
import com.example.kproject.entity.Review;
import com.example.kproject.entity.User;
import com.example.kproject.repository.PlaceRepository;
import com.example.kproject.repository.ReviewRepository;
import com.example.kproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private UserRepository userRepository;

    public Review saveReview(Long placeId, String username, int rating, String comment) {
        Place place = placeRepository.findById(placeId).orElseThrow(() -> new RuntimeException("Place not found"));
        User user = userRepository.findByUsername(username);

        Review review = new Review();
        review.setPlace(place);
        review.setUser(user);
        review.setRating(rating);
        review.setComment(comment);

        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByPlaceId(Long placeId) {
        return reviewRepository.findByPlace_PlaceIdOrderByRatingDesc(placeId);
    }
}
