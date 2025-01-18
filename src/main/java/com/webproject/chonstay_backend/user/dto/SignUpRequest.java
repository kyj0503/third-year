package com.webproject.chonstay_backend.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {
    private String name;
    private String email;
    private String userPassword;
    private String phoneNumber;
}
