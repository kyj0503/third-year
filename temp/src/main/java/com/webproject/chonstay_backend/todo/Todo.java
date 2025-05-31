package com.webproject.chonstay_backend.todo;


import static jakarta.persistence.GenerationType.IDENTITY;

import com.webproject.chonstay_backend.homeTodo.HomeTodo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long todoId;

    @Column(nullable = false)
    private String todoBody;

    @OneToMany(mappedBy = "todo", fetch = FetchType.LAZY)
    private List<HomeTodo> homeTodo = new ArrayList<>();
}
