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

    /**
     * 사용자 이름(username)과 비밀번호(password)를 사용하여 인증을 수행하는 메서드.
     *
     * @param username 사용자가 입력한 사용자 이름
     * @param password 사용자가 입력한 비밀번호
     * @return 인증에 성공한 User 객체 또는 실패 시 null
     */
    public User authenticateUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    /**
     * 모든 사용자 데이터를 조회하는 메서드.
     *
     * @return 저장된 모든 User 객체의 리스트
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 새로운 사용자를 데이터베이스에 저장하는 메서드.
     * 비밀번호 암호화는 적용되지 않은 상태로 저장된다.
     *
     * @param user 저장할 User 객체
     * @return 저장된 User 객체
     */
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 고유 ID를 기반으로 사용자 데이터를 조회하는 메서드.
     *
     * @param id 조회할 사용자의 ID
     * @return 해당 ID에 대한 User 객체를 Optional로 반환
     */
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    /**
     * 사용자 정보를 저장하거나 업데이트하는 메서드.
     *
     * @param user 저장 또는 업데이트할 User 객체
     * @return 저장된 User 객체
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
