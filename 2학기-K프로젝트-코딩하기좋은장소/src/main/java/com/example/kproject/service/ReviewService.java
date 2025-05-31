package com.example.kproject.service;

import com.example.kproject.entity.Review;
import com.example.kproject.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * 새로운 리뷰를 저장하거나 기존 리뷰 정보를 갱신하는 메서드.
     *
     * @param review 저장 또는 갱신할 Review 객체
     * @return 저장된 Review 객체
     */
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    /**
     * 특정 장소에 해당하는 모든 리뷰를 조회하는 메서드.
     *
     * @param placeId 리뷰를 조회할 장소의 ID
     * @return 해당 장소에 대한 리뷰 리스트
     */
    public List<Review> getReviewsByPlaceId(Integer placeId) {
        return reviewRepository.findByPlacePlaceId(placeId);
    }

    /**
     * 평균 평점이 높은 순으로 장소 리스트를 반환하는 메서드.
     *
     * @return 장소 ID, 이름, 평균 평점을 포함한 리스트
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

    /**
     * 특정 사용자 ID에 해당하는 모든 리뷰를 조회하는 메서드.
     *
     * @param userId 리뷰를 조회할 사용자의 ID
     * @return 해당 사용자가 작성한 리뷰 리스트
     */
    public List<Review> getReviewsByUserId(Integer userId) {
        return reviewRepository.findByUserUserId(userId);
    }
}
