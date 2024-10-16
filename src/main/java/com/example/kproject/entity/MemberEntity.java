package com.example.kproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private int age;

    @Column(name = "userid")
    private String userId;

    @Column(name = "password")
    private String userPwd;

    public Member(String name, String email, int age, String userId, String userPwd) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.userId = userId;
        this.userPwd = userPwd;
    }
}
