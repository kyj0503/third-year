package com.example.kproject.repository;

import com.example.kproject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByPlacePlaceId(Integer placeId);
}
