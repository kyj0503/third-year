package com.example.kproject.service;

import com.example.kproject.entity.User;
import com.example.kproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * UserService는 사용자(User) 엔티티와 관련된 비즈니스 로직을 처리한다.
 * UserRepository를 사용하여 데이터베이스와 상호작용한다.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 주어진 사용자 이름(username)과 비밀번호(password)로 인증을 수행한다.
     *
     * @param username 사용자가 입력한 사용자 이름.
     * @param password 사용자가 입력한 비밀번호.
     * @return 인증된 User 객체 또는 인증 실패 시 null.
     */
    public User authenticateUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 단순한 비밀번호 비교를 수행한다.
            if (password.equals(user.getPassword())) {
                return user; // 인증 성공
            }
        }

        return null; // 인증 실패
    }

    /**
     * 모든 사용자(User) 데이터를 조회한다.
     *
     * @return 데이터베이스에 저장된 모든 User 객체의 리스트.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 새로운 사용자(User)를 데이터베이스에 저장한다.
     * 비밀번호 암호화 로직이 추가되지 않은 상태로 저장됨.
     *
     * @param user 저장할 User 객체.
     * @return 저장된 User 객체.
     */
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 고유 ID를 기반으로 사용자(User) 데이터를 조회한다.
     *
     * @param id 조회할 사용자의 고유 식별자.
     * @return 해당 ID에 해당하는 User 객체를 감싼 Optional 객체.
     */
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }
}
