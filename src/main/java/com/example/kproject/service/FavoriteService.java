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

    public void saveFavorite(Favorite favorite) {
        favoriteRepository.save(favorite);
    }

    public List<Favorite> getFavoritesByUserId(Integer userId) {
        return favoriteRepository.findByUserUserId(userId);
    }
}
