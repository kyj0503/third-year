package com.example.transactionproject.Service;

import com.example.transactionproject.Entity.Post;
import com.example.transactionproject.Repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionTestService {
    private final PostRepository postRepository;

    public void insertWithoutTransaction() {
        postRepository.save(new Post(null, "Post 1", null, LocalDateTime.now()));
        postRepository.save(new Post(null, "Post 2", null, LocalDateTime.now()));
        throw new RuntimeException("Test exception");
    }

    @Transactional
    public void insertWithTransaction() {
        postRepository.save(new Post(null, "Post 1", null, LocalDateTime.now()));
        postRepository.save(new Post(null, "Post 2", null, LocalDateTime.now()));
        throw new RuntimeException("Test exception");
    }
}
