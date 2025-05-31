package com.example.MiniProjectWeb.Entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * User 엔티티 클래스
 * - 사용자 정보를 저장하는 엔티티
 * - 사용자 ID, 사용자 이름, 비밀번호, 이미지 경로 등의 정보를 포함
 */
@Entity
@Table(name = "users") // 데이터베이스의 "users" 테이블과 매핑
@Data
public class User {

    /** 사용자 고유 ID (Primary Key) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 설정
    private Long id;

    /** 사용자 이름 */
    @Column(nullable = false, unique = true) // null 값 허용 불가 및 중복 불가 설정
    private String username;

    /** 사용자 비밀번호 */
    @Column(nullable = false) // null 값 허용 불가
    private String password;

    /**
     * 사용자 이미지 경로
     * - 사용자 프로필 이미지의 경로를 저장
     * - 필수 값이 아니므로 null 값을 허용
     */
    private String imagePath;
}
