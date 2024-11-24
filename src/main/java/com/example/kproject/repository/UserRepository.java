package com.example.kproject.repository;

import com.example.kproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UserRepository는 User 엔티티에 대한 데이터베이스 액세스를 처리한다.
 * JpaRepository를 확장하여 기본 CRUD 기능과 사용자 정의 쿼리를 제공한다.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 사용자 이름(Username)을 기반으로 특정 사용자(User)를 조회한다.
     * 주로 인증 및 로그인 기능에서 사용된다.
     *
     * @param username 조회할 사용자의 고유한 사용자 이름.
     * @return username에 해당하는 User 객체를 Optional로 감싼 형태로 반환.
     *         사용자 이름이 존재하지 않을 경우 Optional.empty()를 반환.
     */
    Optional<User> findByUsername(String username);
}
