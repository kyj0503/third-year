package com.example.jpamemberproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository; // memberRepository 객체 생성 (DI 의존성 주입)

    @Autowired // 좀 더 안전한 방법
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

        List<Member> members = new ArrayList<>() {{
            add(new Member("Alice", "alice@aaa.net", 30, "alice", "1111"));
            add(new Member("asdf", "afew@aaa.net", 24, "brsth", "457"));
            add(new Member("qwg", "svf@aaa.net", 32, "grsa", "2345"));
            add(new Member("dffse", "wef@aaa.net", 11, "sg", "3456"));
            add(new Member("faewf", "sbt@aaa.net", 22, "fa", "465"));
        }};

        // 멤버 저장 (배치 처리)
        memberRepository.saveAll(members);
    }

    @Override
    public List<Member> getMemberList() {
        List<Member> members = memberRepository.findAll();
        return members;
    }

    @Override
    public Member findMemberById(String userId) {
        return null;
    }

    @Override
    public Member findMemberByEmail(String email) {
        return null;
    }

    @Override
    public Member findMemberByName(String name) {
        return null;
    }

    @Override
    public List<Member> getMemberListOrderByAgeDesc() {
        return List.of();
    }

    @Override
    public boolean validMember(String userId, String userPwd) {
        return false;
    }

    @Override
    public List<Member> getGeneration(int generation) {
        return List.of();
    }
}
