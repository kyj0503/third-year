package com.example.JPAmemberProject;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

//위에 MemberRepository interface를 상속받아서 구현체를 만드는 곳
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional //오류나 예외상황에서 데이터의 일관성과 롤백을 지원
                    //오류나면 Roleback, 정상 사용이면 Commit
    void ContextLoads() {
        //1. 새로운 멤버 객체 생성
        Member member1 = new Member("Alice", "alice@aaa.net", 30);
        Member member2 = new Member("Bob", "bob@aaa.net", 25);
        Member member3 = new Member("Charlie", "charlie@aaa.net", 45);

        //2. 멤버를 지정
        memberRepository.save(member1); //memberRepositor에서 가져온 JPA 메서드를 적용한다.
        memberRepository.save(member2); // 기본적인 CRUD 메서드라서 작성하지 않는다.
        memberRepository.save(member3);

        //3. 멤버 로그 확인
        member1.logMemberInfo();
        member2.logMemberInfo();
        member3.logMemberInfo();

        //3. ID로 멤버 검색 (ID = 2)
        Optional<Member> foundMember = memberRepository.findById(2L); //ID값이 2이면서 Long타입인 멤버를 가져온다. (Bob)
        if(foundMember.isPresent()) System.out.println(foundMember); //Optional을 하면 레포지토리에서 member를 찾아보고 if-else로 나눈다.
        else System.out.println("Member를 찾을 수 없습니다.");

        //4-1. Member List 가져오기
        List<Member> memberList = memberRepository.findAll(); //제너릭클래스 memberList에 모든 DB값을 가져오고 출력
        for(Member member: memberList) System.out.println(member);

        //4-2. Member List 가져오기2 (나이순으로 정렬)
        List<Member> memberListByAge =
                memberRepository.findAll(Sort.by(Sort.Direction.DESC, "age"));
        //age를 기준으로 내림차순(DESC)로 정렬하는 내용이 findAll() 안에 들어있고 이 모든 값을 제너릭클래스 memberListByAge에 넣는다.
        for(Member member: memberListByAge) System.out.println(member);

        //5. Member 객수 출력
        long count = memberRepository.count();
        System.out.println("Number of Members: " + count);
    }
}