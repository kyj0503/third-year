package com.webproject.kangneng_back.repository;

import com.webproject.kangneng_back.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 사용자 이름으로 사용자 조회 (Optional로 반환)
    Optional<UserEntity> findByUsername(String username);

    // 이메일로 사용자 조회 (Optional로 반환)
    Optional<UserEntity> findByEmail(String email);

    // 닉네임으로 사용자 조회 (Optional로 반환)
    Optional<UserEntity> findByNickname(String nickname);

    // 사용자 이름이 존재하는지 여부 확인
    boolean existsByNickname(String username);
}
