package com.example.JPAmemberProject;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Getter @Setter
@NoArgsConstructor //기본 생성자 사용, Member()를 자동으로 생성
@ToString //toString() 메서드 자동생성
@Slf4j //log.info()를 자동으로 정렬해준다.
//DB에 넣을 값을 미리 정의해놓는다.
public class Member {

    @Id //엔터티의 기본키 필드에 값을 직접 넣어 등록한다(직접할당)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY로 기본키 생성을 DB에 위임한다(자동할당)
    private Long id;
    private String name;
    private String email;
    private int age;

    @Column(name = "userId")
    private String userId;

    @Column(name = "passWord")
    private String passWord;

    //JPA의 중심으로 자동으로 Getter/Setter 등을 진행한다.
    //매개변수가 있는 생성자 메서드로, 기본생성자인 Member()는 @NoArgsConstructor가 자동으로 생성
    public Member(String name, String email, int age, String userId, String passWord) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.userId = userId;
        this.passWord = passWord;
    }

    public Member(String naem, String email, int age) {
        this.name = naem;
        this.email = email;
        this.age = age;
    }
    //원래 각 name, email, age를 다룰 getter, setter가 필요하지만 lombok으로 인해서 처리하지 않아도 된다.
    public void logMemberInfo() {
        log.info("Member info - ID: {}, Name: {}, Email: {}, Age: {}, userId: {}, passWord: {}",
                id, name, email, age, userId, passWord);
    }
}
