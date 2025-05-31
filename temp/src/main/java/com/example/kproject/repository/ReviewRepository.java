package com.example.kproject.repository;

import com.example.kproject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    /**
     * 특정 장소 ID에 대한 모든 리뷰를 조회하는 메서드.
     *
     * @param placeId 리뷰가 속한 장소의 ID
     * @return 해당 장소와 연관된 리뷰 리스트
     */
    List<Review> findByPlacePlaceId(Integer placeId);

    /**
     * 장소별 평균 평점을 계산하여 반환하는 메서드.
     * 장소 ID와 이름별로 그룹화하여 평균 평점을 계산하고 내림차순으로 정렬한다.
     *
     * @return 각 장소의 ID, 이름, 평균 평점을 담은 Object 배열 리스트
     */
    @Query("SELECT r.place.placeId, r.place.name, AVG(r.rating) AS averageRating " +
            "FROM Review r " +
            "GROUP BY r.place.placeId, r.place.name " +
            "ORDER BY averageRating DESC")
    List<Object[]> findAverageRatingPerPlace();

    /**
     * 특정 사용자 ID에 대한 모든 리뷰를 조회하는 메서드.
     *
     * @param userId 리뷰를 작성한 사용자의 ID
     * @return 해당 사용자와 연관된 리뷰 리스트
     */
    List<Review> findByUserUserId(Integer userId);
}
