package com.example.MiniProjectWeb.Service;

import com.example.MiniProjectWeb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean registerUser(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            return false; // 사용자 이름 중복 체크
        }
        com.example.MiniProjectWeb.Entity.User user = new com.example.MiniProjectWeb.Entity.User();
        user.setUsername(username);
        user.setPassword(password);
        user.setImagePath("/images/default.png"); // 기본 이미지 경로 << 랜덤으로 돌리게 하기 
        userRepository.save(user);
        return true;
    }
}
