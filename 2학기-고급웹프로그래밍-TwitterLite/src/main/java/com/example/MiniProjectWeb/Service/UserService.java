package com.example.MiniProjectWeb.Service;

import com.example.MiniProjectWeb.Entity.User;
import com.example.MiniProjectWeb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * UserService 클래스
 * - 사용자 관리와 관련된 비즈니스 로직을 처리
 * - 사용자 등록 및 비밀번호 검증 등의 기능 제공
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 비밀번호를 SHA-256 알고리즘으로 해시 처리
     *
     * @param password 해시할 비밀번호
     * @return 해시된 비밀번호 (16진수 문자열)
     * @throws RuntimeException 해시 알고리즘 오류 발생 시
     */
    private String hashPassword(String password) {
        try {
            // SHA-256 해시 인스턴스 생성
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // 비밀번호를 바이트 배열로 변환한 뒤 해시 처리
            byte[] encodedHash = digest.digest(password.getBytes());
            // 해시 값을 16진수 문자열로 변환
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
            throw new RuntimeException("해시 알고리즘 오류", e); // SHA-256 알고리즘을 지원하지 않는 경우 예외 발생
        }
    }

    /**
     * 새로운 사용자 등록
     *
     * @param username 사용자 이름
     * @param password 비밀번호
     * @return 성공 여부 (중복된 사용자 이름이면 false 반환)
     */
    public boolean registerUser(String username, String password) {
        // 사용자 이름 중복 확인
        if (userRepository.findByUsername(username) != null) {
            return false; // 중복된 사용자 이름
        }

        // 새로운 사용자 생성
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashPassword(password)); // 비밀번호를 해시하여 저장
        user.setImagePath("/images/default.png"); // 기본 이미지 경로 설정
        userRepository.save(user); // 사용자 저장
        return true;
    }

    /**
     * 비밀번호 검증
     *
     * @param rawPassword    입력된 원본 비밀번호
     * @param hashedPassword 저장된 해시된 비밀번호
     * @return 비밀번호가 일치하면 true, 그렇지 않으면 false
     */
    public boolean validatePassword(String rawPassword, String hashedPassword) {
        return hashPassword(rawPassword).equals(hashedPassword); // 입력된 비밀번호를 해시한 값과 저장된 해시 값 비교
    }
}
