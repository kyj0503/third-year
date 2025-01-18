package com.webproject.chonstay_backend.user;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입
    public void registerUser(User user) {
        // 비밀번호 암호화 후 저장
        user.setUserPassword(hashPassword(user.getUserPassword()));
        userRepository.save(user);
    }

    // 로그인
    public Optional<User> login(String userEmail, String rawPassword) {
        return userRepository.findByUserEmail(userEmail)
                .filter(user -> user.getUserPassword().equals(hashPassword(rawPassword)));
    }

    // SHA-256 암호화
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error while hashing password", e);
        }
    }
}
