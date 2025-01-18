package com.webproject.chonstay_backend.home.dto;

import com.webproject.chonstay_backend.home.Home;
import com.webproject.chonstay_backend.review.Review;

public record HomePreview(
        String homeName,
        String address,
        String homeImageUrl,
        Float averageReview
) {
    public static HomePreview of(Home home) {
        float averageReview;
        if (!home.getReviews().isEmpty()) {
            averageReview = (float) home.getReviews().stream()
                    .mapToInt(Review::getPoint)
                    .average()
                    .orElse(0.0);
            averageReview = Math.round(averageReview * 10.0f) / 10.0f;
        } else {
            averageReview = 0.0f;  // 리뷰가 없을 경우 0.0
        }
        return new HomePreview(
                home.getHomeName(),
                home.getAddress(),
                home.getHomeImageUrl(),
                averageReview
        );
    }
}
