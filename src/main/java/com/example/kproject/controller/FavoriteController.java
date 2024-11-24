package com.example.kproject.controller;

import com.example.kproject.entity.Favorite;
import com.example.kproject.entity.Place;
import com.example.kproject.entity.User;
import com.example.kproject.service.FavoriteService;
import com.example.kproject.service.PlaceService;
import com.example.kproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> addFavorite(@RequestBody Map<String, Object> favoriteData, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }

        String name = (String) favoriteData.get("name");
        String address = (String) favoriteData.get("address");
        Double latitude = Double.parseDouble(favoriteData.get("latitude").toString());
        Double longitude = Double.parseDouble(favoriteData.get("longitude").toString());

        User user = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Place place = placeService.findOrCreatePlace(name, address, latitude, longitude);

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setPlace(place);
        favoriteService.saveFavorite(favorite);

        Map<String, String> response = new HashMap<>();
        response.put("message", "즐겨찾기 추가 완료");
        return ResponseEntity.ok(response);
    }
}
