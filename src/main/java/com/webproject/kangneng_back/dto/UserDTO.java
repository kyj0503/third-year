package com.webproject.kangneng_back.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String role;
    private String name;
    private String username; //이게 고유 아이디임
    private String nickname;
    private String email;
    private boolean isNewUser;
}
