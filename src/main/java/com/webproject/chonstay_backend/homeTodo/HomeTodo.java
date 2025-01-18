package com.webproject.chonstay_backend.homeTodo;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.webproject.chonstay_backend.home.Home;
import com.webproject.chonstay_backend.todo.Todo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HomeTodo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long homeTodoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id", nullable = false)
    private Home home;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo;
}
