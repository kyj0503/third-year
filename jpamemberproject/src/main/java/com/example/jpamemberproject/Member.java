package com.example.jpamemberproject;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Getter @Setter @NoArgsConstructor @ToString
@Slf4j
public class Member {

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


    public Member(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Member(String name, String email, int age, String userId, String userPwd) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.userId = userId;
        this.userPwd = userPwd;
    }

    public void logInfo() {
        log.info("Member info - ID: {},Name : {},Email : {},Age : {}", id, name, email, age);
    }
}
