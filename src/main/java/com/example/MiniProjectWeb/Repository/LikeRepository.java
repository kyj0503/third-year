package com.example.MiniProjectWeb.Repository;

import com.example.MiniProjectWeb.Entity.Like;
import com.example.MiniProjectWeb.Entity.Post;
import com.example.MiniProjectWeb.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * LikeRepository 인터페이스
 * - 좋아요(Like) 엔티티와 데이터베이스 간의 상호작용을 처리
 * - JpaRepository를 상속받아 기본 CRUD 기능을 제공
 */
public interface LikeRepository extends JpaRepository<Like, Long> {

    /**
     * 특정 사용자(User)와 게시물(Post)에 대해 좋아요가 이미 존재하는지 확인
     *
     * @param user 사용자 객체
     * @param post 게시물 객체
     * @return 이미 좋아요가 존재하면 true, 아니면 false
     */
    boolean existsByUserAndPost(User user, Post post);

    /**
     * 특정 게시물(Post)의 좋아요 개수를 조회
     *
     * @param post 좋아요 개수를 조회할 게시물 객체
     * @return 좋아요 개수
     */
    long countByPost(Post post);

    /**
     * 특정 게시물(Post)와 연관된 모든 좋아요(Like)를 삭제
     *
     * @param post 삭제할 좋아요가 연관된 게시물 객체
     */
    @Transactional
    void deleteAllByPost(Post post);

    Like findByUserAndPost(User user, Post post);
}
