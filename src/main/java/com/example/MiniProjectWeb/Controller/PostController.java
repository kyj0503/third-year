package com.example.MiniProjectWeb.Controller;

import com.example.MiniProjectWeb.Entity.Comment;
import com.example.MiniProjectWeb.Entity.Post;
import com.example.MiniProjectWeb.Entity.User;
import com.example.MiniProjectWeb.Repository.CommentRepository;
import com.example.MiniProjectWeb.Repository.PostRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    // 메인 화면: 모든 게시물을 표시
    @GetMapping("posts")
    public String home(Model model) {
        List<Post> posts = postRepository.findAll(); // 모든 게시물 가져오기
        model.addAttribute("posts", posts); // 게시물 리스트를 모델에 추가
        return "index"; // index.html 반환
    }

    // 게시물 추가
    @PostMapping("/addpost")
    public String addPost(@RequestParam String content,
                          HttpSession session) {
        User user = (User) session.getAttribute("user"); // 현재 로그인한 사용자 가져오기
        if (user != null) {
            Post post = new Post();
            post.setContent(content);
            post.setUser(user); // 게시물 작성자를 현재 사용자로 설정
            postRepository.save(post); // 게시물 저장
        }
        return "redirect:/"; // 메인 화면으로 리다이렉트
    }

    @PostMapping("/posts/{id}/like")
    public String likePost(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElse(null); // ID를 통해 게시물을 찾기
        if (post != null) {
            post.setLikes(post.getLikes() + 1); // 좋아요 증가
            postRepository.save(post); // 변경된 게시물 저장
        }
        return "redirect:/"; // 메인 화면으로 리다이렉트
    }

    @PostMapping("/posts/{id}/comment")
    public String addComment(@PathVariable Long id,
                             @RequestParam String content,
                             HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/"; // 로그인 안 된 경우
        }

        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setUser(user);
            comment.setPost(post);
            commentRepository.save(comment);
        }
        return "redirect:/"; // 메인 화면으로 리다이렉트
    }

}
