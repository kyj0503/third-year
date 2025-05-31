package com.example.boardproject.DTO;

import com.example.boardproject.Entity.Member;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Setter @Getter @Slf4j @NoArgsConstructor @ToString @AllArgsConstructor
public class MemberForm {
    private String email;
    private String password;

    public Member toEntity() {
        return new Member(email, password);
    }
    public void loginfo() {
        log.info("Email: {}", email);
    }
}
