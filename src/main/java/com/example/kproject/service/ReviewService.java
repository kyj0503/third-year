package com.example.kproject.service;

import com.example.kproject.entity.Review;
import com.example.kproject.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ReviewService는 Review 엔티티와 관련된 비즈니스 로직을 처리한다.
 * ReviewRepository를 사용하여 데이터베이스와 상호작용한다.
 */
@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * 새로운 리뷰(Review)를 저장하거나 기존 리뷰 정보를 갱신한다.
     *
     * @param review 저장 또는 갱신할 Review 객체.
     * @return 저장된 Review 객체.
     */
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    /**
     * 특정 장소(Place)에 해당하는 모든 리뷰 목록을 조회한다.
     * 장소 ID를 기반으로 관련된 리뷰를 필터링한다.
     *
     * @param placeId 조회할 장소의 고유 식별자.
     * @return 주어진 장소에 대한 리뷰 리스트.
     */
    public List<Review> getReviewsByPlaceId(Integer placeId) {
        return reviewRepository.findByPlacePlaceId(placeId);
    }

    /**
     * 평균 평점이 높은 순으로 장소 리스트를 반환
     */
    public List<Map<String, Object>> getPlacesByAverageRating() {
        return reviewRepository.findAverageRatingPerPlace()
                .stream()
                .map(obj -> Map.of(
                        "placeId", obj[0],
                        "placeName", obj[1],
                        "averageRating", obj[2]
                ))
                .collect(Collectors.toList());
    }

    public List<Review> getReviewsByUserId(Integer userId) {
        return reviewRepository.findByUserUserId(userId);
    }
}
