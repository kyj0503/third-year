package com.example.kproject.service;

import com.example.kproject.entity.User;
import com.example.kproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 로그인 인증 메서드
    public User authenticateUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 단순 비밀번호 비교
            if (password.equals(user.getPassword())) {
                return user;
            }
        }

        return null; // 인증 실패
    }

    // 모든 사용자 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 사용자 저장 (비밀번호 암호화 제거)
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // ID로 사용자 조회
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }
}
