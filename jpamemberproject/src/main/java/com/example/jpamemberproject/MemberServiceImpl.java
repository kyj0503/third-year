package com.example.jpamemberproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {

    }

    @Override
    public List<Member> getMemberList() {
        return List.of();
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
