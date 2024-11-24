package com.example.kproject.repository;

import com.example.kproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 사용자 이름(Username)을 기준으로 특정 사용자를 조회하는 메서드.
     *
     * @param username 조회할 사용자의 고유 사용자 이름
     * @return 해당 사용자 이름에 해당하는 User 객체를 Optional로 반환
     */
    Optional<User> findByUsername(String username);
}
