package com.example.jpamemberproject;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setup() {

        // 익명 클래스 방식
        List<Member> members = new ArrayList<>() {{
            add(new Member("Alice", "alice@aaa.net", 30, "alice", "1111"));
            add(new Member("Alice", "alice@aaa.net", 30, "alice", "1111"));
            add(new Member("Alice", "alice@aaa.net", 30, "alice", "1111"));
            add(new Member("Alice", "alice@aaa.net", 30, "alice", "1111"));
            add(new Member("Alice", "alice@aaa.net", 30, "alice", "1111"));
        }};

        // 멤버 저장
        memberRepository.saveAll(members);

        // 멤버 로그 확인
        for (Member m : members) m.logInfo();
    }

    @Test
    void findByUserId() {
        Optional<Member> foundMember = memberRepository.findByUserId("angela");
    }

    @Test
    @Transactional
    void ContextLoads() {
        // 1. 새로운 멤버 객체 생성
        Member member1 = new Member("Alice", "alice@aaa.net", 30);
        Member member2 = new Member("Bob", "bob@aaa.net", 25);
        Member member3 = new Member("Charlie", "charlie@aaa.net", 45);

        // 2. 멤버를 저장
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        // 3. 멤버 로그 확인
        member1.logInfo();
        member2.logInfo();
        member3.logInfo();

        // 4. ID로 멤버 검색 (ID = 3)
        Optional<Member> foundMember = memberRepository.findById(3L);
        if(foundMember.isPresent()) System.out.println(foundMember.get());
        else System.out.println("Member를 찾을 수 없습니다.");

        // 5-1. Member List 가져오기
        List<Member> memberLlist = memberRepository.findAll();
        for(Member member: memberLlist) System.out.println(member);

        // 5-2. Member List 가져오기2 (나이순으로 정렬)
        List<Member> memberListByAge = memberRepository.findAll(Sort.by(Sort.Direction.DESC, "age"));
        for(Member member: memberListByAge) System.out.println(member);

        // 6. Member 객수 출력
        long count = memberRepository.count();
        System.out.println("Number of Members: " + count);
    }

}
