package com.webproject.kangneng_back.oauth.service;

import com.webproject.kangneng_back.oauth.entity.UserEntity;
import com.webproject.kangneng_back.oauth.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void updateNickname(String username, String nickname) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + username));

        // 닉네임 중복 체크
        if (userRepository.existsByNickname(nickname)) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다: " + nickname);
        }

        user.setNickname(nickname);
        user.setNewUser(false);  // 닉네임 설정 시 신규 사용자 표시 해제
        userRepository.save(user);
    }
}