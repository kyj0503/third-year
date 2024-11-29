package com.example.MiniProjectWeb.Repository;

import com.example.MiniProjectWeb.Entity.Comment;
import com.example.MiniProjectWeb.Entity.Post; // Post 클래스를 명시적으로 import
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CommentRepository 인터페이스
 * - 댓글(Comment) 엔티티와 데이터베이스 간의 상호작용을 처리
 * - JpaRepository를 상속받아 기본 CRUD 기능을 제공
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 특정 게시물(Post)와 연관된 모든 댓글(Comment)을 삭제
     *
     * @param post 삭제할 댓글이 연관된 게시물 객체
     */
    void deleteAllByPost(Post post);

}
