package com.example.JPAmemberProject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest2 {
    @Autowired
    MemberRepository memberRepository; //하면 객체 생성 (의존성 주입, Autowired로 적용)

    @BeforeEach
    void setUp() {
        List<Member> ms = new ArrayList<>() {{
                add(new Member("Alice", "alice@aa.com", 30, "alice", "1111"));
                add(new Member("Bob", "bob@bb.com", 25, "bob", "1111"));
                add(new Member("Charlie", "charlie@cc.com", 45, "charlie", "1111"));
                add(new Member("David", "david@aa.com", 23, "alice", "1111"));
                add(new Member("Engela", "engela@bb.com", 40, "bob", "1111"));
                add(new Member("Steeve", "steeve@cc.com", 21, "charlie", "1111"));
            }};

        //멤버 저장
        //테스트를 수행하기전에 실행되서 Save해둔다.
        memberRepository.saveAll(ms);


        //멤버 로그 확인
        for(Member m : ms) m.logMemberInfo();
    }

    //memberResoitory가 불리기 전에 findByUserId가 불려서 bean이 인식을 못해서 오류가 뜨기 떄문에
    //Repository파일을 하나로 합치거나 패키지를 나눠서 실행해보자
    @Test
    void findByUserId() {
        Optional<Member> foundOne = memberRepository.findByUserId("Alice");
        System.out.println(foundOne.get().toString());
    }

    @Test
    void findByEmail() {
        Optional<Member> foundone = memberRepository.findByEmail("alice@aa.com");
        System.out.println(foundone.get().toString());
        }

    //Repository에서 관련 @쿼리 어노테이션을 통해서 생성한다.
    @Test
    void twnetyGenerateMembers() {}

    @Test
    void getGenerationMembers() {}
}