package com.webproject.chonstay_backend.user;

import com.webproject.chonstay_backend.liked.Liked;
import com.webproject.chonstay_backend.reservation.Reservation;
import com.webproject.chonstay_backend.review.Review;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long userId;

    @NotBlank(message = "이름은 필수입니다.")
    @Column(length = 20, nullable = false)
    private String name;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    @Column(length = 40, nullable = false, unique = true)
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Column(length = 64, nullable = false)
    private String userPassword;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Column(length = 13, nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("'GUEST'")
    private Role role;

    @Column(columnDefinition = "text", nullable = true)
    private String photo;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Liked> likeds = new ArrayList<>();
}
