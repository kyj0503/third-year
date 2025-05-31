package com.webproject.minisns.service;

import com.webproject.minisns.dto.PostDTO;
import com.webproject.minisns.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> new PostDTO(
                        post.getPlayer().getUsername(), // Player의 username 가져오기
                        post.getContent(),
                        post.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}
