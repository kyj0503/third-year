package com.example.kproject.service;

import com.example.kproject.entity.Favorite;
import com.example.kproject.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    /**
     * 즐겨찾기 데이터를 저장하는 메서드.
     *
     * @param favorite 저장할 Favorite 엔티티
     */
    public void saveFavorite(Favorite favorite) {
        favoriteRepository.save(favorite);
    }

    /**
     * 특정 사용자 ID에 대한 즐겨찾기 목록을 조회하는 메서드.
     *
     * @param userId 조회할 사용자의 ID
     * @return 해당 사용자의 즐겨찾기 리스트
     */
    public List<Favorite> getFavoritesByUserId(Integer userId) {
        return favoriteRepository.findByUserUserId(userId);
    }
}
