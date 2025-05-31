package com.example.boardproject.Controller;


import com.example.boardproject.Entity.Member;
import com.example.boardproject.Repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    //1. id를 통한 member 단일 조회
    @GetMapping("/members/{id}")
    public String selectMember(@PathVariable("id") Long id, Model model) {

        model.addAttribute("member", memberRepository.findById(id));
        return "members/show";
    }

    //2. 데이터 전체 조회
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> memberList = memberRepository.findAll();

        model.addAttribute("memberList", memberList);

        return "members/index";
    }

}
