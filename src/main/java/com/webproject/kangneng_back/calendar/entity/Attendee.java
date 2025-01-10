package com.webproject.kangneng_back.calendar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name; // 참석자 이름 추가

    private String email; // 이메일 필드 유지 (필요 시 제거 가능)

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonBackReference // 순환 참조 방지
    private Event event;
}
