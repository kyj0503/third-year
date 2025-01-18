package com.webproject.chonstay_backend.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "이름은 필수입니다.")
    @Column(length = 20, nullable = false)
    private String name;

    @NotBlank(message = "닉네임은 필수입니다.")
    @Column(length = 40, nullable = false)
    private String nickname;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    @Column(length = 40, nullable = false, unique = true)
    private String userEmail;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Column(length = 40, nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private LocalDateTime birthday;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role {
        ADMIN, USER
    }
}
