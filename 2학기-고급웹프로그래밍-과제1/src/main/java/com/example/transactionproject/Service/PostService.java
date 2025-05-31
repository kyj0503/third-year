    package com.example.transactionproject.Service;

    import com.example.transactionproject.Entity.Post;
    import com.example.transactionproject.Entity.User;
    import com.example.transactionproject.Repository.PostRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class PostService {
        private final PostRepository postRepository;

        public List<Post> getAllPosts() {
            return postRepository.findAll();
        }

        public Post getPostById(Long id) {
            return postRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        }

        public Post addPost(String content, User user) {
            if (user == null) {
                throw new IllegalArgumentException("User cannot be null");
            }

            Post post = new Post();
            post.setContent(content);
            post.setUser(user);
            return postRepository.save(post);
        }

        public Post updatePost(Long id, String content) {
            Post post = getPostById(id);
            post.setContent(content);
            return postRepository.save(post);
        }

        public void deletePost(Long id) {
            postRepository.deleteById(id);
        }
    }
