package com.example.transactionproject.Service;

import com.example.transactionproject.Entity.Post;
import com.example.transactionproject.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionTestService {

    private final PostRepository postRepository;

    // 트랜잭션 사용하지 않을 때
    public void insertWithoutTransaction(List<Post> postFormList) {
        // ID가 null인지 확인하고 새로운 엔티티로 처리
        for (Post post : postFormList) {
            if (post.getId() != null) {
                post.setId(null); // ID를 null로 설정
            }
        }

        // 데이터 저장: 강제 예외 발생 전 데이터 커밋
        List<Post> savedPosts = postRepository.saveAll(postFormList);

        // 강제로 예외를 발생
        postRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("강제 예외 발생"));
    }

    // 트랜잭션을 사용할 때
    @Transactional
    public void insertWithTransaction(List<Post> postFormList) {
        // ID가 null인지 확인하고 새로운 엔티티로 처리
        for (Post post : postFormList) {
            if (post.getId() != null) {
                post.setId(null); // ID를 null로 설정
            }
        }

        // 데이터 저장
        postRepository.saveAll(postFormList);

        // 강제로 예외 발생
        postRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("강제 예외 발생"));
    }
}
