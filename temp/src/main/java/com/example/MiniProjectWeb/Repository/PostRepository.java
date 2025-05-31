package com.example.MiniProjectWeb.Repository;

import com.example.MiniProjectWeb.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * PostRepository 인터페이스
 * - 게시물(Post) 엔티티와 데이터베이스 간의 상호작용을 처리
 * - JpaRepository를 상속받아 기본 CRUD 기능을 제공
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * 게시물을 생성일(createdAt) 기준으로 최신순으로 정렬하여 조회
     *
     * @return 최신순으로 정렬된 게시물 리스트
     */
    List<Post> findAllByOrderByCreatedAtDesc();
}
