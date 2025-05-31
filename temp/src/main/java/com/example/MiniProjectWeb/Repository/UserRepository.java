package com.example.MiniProjectWeb.Repository;

import com.example.MiniProjectWeb.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository 인터페이스
 * - 사용자(User) 엔티티와 데이터베이스 간의 상호작용을 처리
 * - JpaRepository를 상속받아 기본 CRUD 기능을 제공
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 사용자 이름(username)을 기준으로 사용자(User)를 조회
     *
     * @param username 조회할 사용자의 이름
     * @return 해당 이름을 가진 사용자(User) 엔티티 객체
     */
    User findByUsername(String username);
}
