package com.example.kproject.repository;

import com.example.kproject.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    /**
     * 특정 사용자의 즐겨찾기 목록을 조회하는 메서드.
     *
     * @param userId 조회하려는 사용자의 ID
     * @return 사용자가 추가한 즐겨찾기의 리스트
     */
    List<Favorite> findByUserUserId(Integer userId);
}
