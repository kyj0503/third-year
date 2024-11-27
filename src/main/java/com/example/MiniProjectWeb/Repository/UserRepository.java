package com.example.MiniProjectWeb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<com.example.MiniProjectWeb.Entity.User, Long> {
    com.example.MiniProjectWeb.Entity.User findByUsername(String username);  // 사용자 이름으로 조회
}