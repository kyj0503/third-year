package com.example.JPAmemberProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//Service를 상속받아서 DB에서 값을 가져오는 형태를 취한다.
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/list")
    public String getMemberList(Model model) {
        model.addAttribute("members", memberService.getMemberList());
        return "memberList";
    }

    @GetMapping("/list2")
    public String getMemberListOrderByAge(Model model) {
        model.addAttribute("members", memberService.getMemberList());
        return "";
    }
}

