package com.example.kproject.repository;

import com.example.kproject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ReviewRepository는 Review 엔티티에 대한 데이터베이스 액세스를 처리한다.
 * JpaRepository를 확장하여 기본 CRUD 기능과 추가적인 사용자 정의 쿼리를 제공한다.
 */
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    /**
     * 특정 장소 ID에 대한 모든 리뷰를 조회한다.
     * Place의 placeId를 기준으로 Review 엔티티를 검색하여 해당 장소와 연관된 리뷰 목록을 반환한다.
     *
     * @param placeId 검색할 리뷰가 속한 Place 엔티티의 ID.
     * @return 해당 장소 ID와 연관된 Review 엔티티의 리스트.
     *         결과가 없는 경우 빈 리스트를 반환.
     */
    List<Review> findByPlacePlaceId(Integer placeId);
}
