package com.example.MiniProjectWeb.Controller;

import com.example.MiniProjectWeb.Entity.Comment;
import com.example.MiniProjectWeb.Entity.Like;
import com.example.MiniProjectWeb.Entity.Post;
import com.example.MiniProjectWeb.Entity.User;
import com.example.MiniProjectWeb.Repository.CommentRepository;
import com.example.MiniProjectWeb.Repository.LikeRepository;
import com.example.MiniProjectWeb.Repository.PostRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 게시물과 관련된 요청을 처리하는 컨트롤러 클래스.
 * 게시물 추가, 삭제, 좋아요, 댓글 추가 및 삭제 등의 기능 제공.
 */
@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    /**
     * 메인 페이지에 게시물 목록을 최신순으로 표시.
     * @param model 뷰로 데이터를 전달하는 객체.
     * @param session 현재 사용자 정보를 담고 있는 세션.
     * @return 로그인 여부에 따라 적절한 뷰를 반환.
     */
    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        // 현재 로그인된 사용자 정보 가져오기
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        // 최신순으로 게시물 조회
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();

        for (Post post : posts) {
            // 좋아요 개수는 로그인 여부와 관계없이 설정
            post.setLikeCount(likeRepository.countByPost(post));

            post.setCommentCount(post.getComments().size()); // 댓글 개수 설정

            if (user != null) {
                post.setIsOwner(post.getUser().getId().equals(user.getId())); // 게시물 소유 여부 설정
                post.setIsLiked(likeRepository.existsByUserAndPost(user, post));
            }
            for (Comment comment : post.getComments()) {
                if (user != null) {
                    comment.setIsOwner(comment.getUser().getId().equals(user.getId())); // 댓글 소유 여부 설정
                }
            }

            // 게시물 ID에 따른 에러 메시지 설정
            String error = (String) session.getAttribute("error_" + post.getId());
            if (error != null) {
                post.setError(error);
                session.removeAttribute("error_" + post.getId()); // 메시지 처리 후 세션에서 삭제
            }
        }
        model.addAttribute("posts", posts);

        // 로그인 여부에 따라 다른 화면 반환
        if (user != null) {
            return "indexLoggedIn";
        }
        return "index";
    }

    /**
     * 게시물 추가 요청 처리.
     * @param content 게시물 내용.
     * @param session 현재 사용자 정보를 담고 있는 세션.
     * @return 게시물 목록 페이지로 리다이렉트.
     */
    @PostMapping("/addpost")
    public String addPost(@RequestParam String content, HttpSession session) {
        // 현재 로그인된 사용자 정보 가져오기
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("error", "게시물을 작성하려면 로그인이 필요합니다.");
            return "redirect:/";
        }

        // 게시물 생성 및 저장
        Post post = new Post();
        post.setContent(content);
        post.setUser(user);
        postRepository.save(post);
        return "redirect:/";
    }

    /**
     * 특정 게시물에 좋아요를 추가.
     * @param id 게시물 ID.
     * @param session 현재 사용자 정보를 담고 있는 세션.
     * @return 게시물 목록 페이지로 리다이렉트.
     */
    @PostMapping("/posts/{id}/like")
    @ResponseBody
    public Map<String, Object> likePost(@PathVariable Long id, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.put("success", false);
            response.put("message", "로그인이 필요합니다.");
            return response;
        }

        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            response.put("success", false);
            response.put("message", "게시물을 찾을 수 없습니다.");
            return response;
        }

        boolean alreadyLiked = likeRepository.existsByUserAndPost(user, post);

        if (alreadyLiked) {
            Like like = likeRepository.findByUserAndPost(user, post);
            likeRepository.delete(like);
        } else {
            Like like = new Like();
            like.setUser(user);
            like.setPost(post);
            likeRepository.save(like);
        }

        long likeCount = likeRepository.countByPost(post);
        response.put("success", true);
        response.put("isLiked", !alreadyLiked);
        response.put("likeCount", likeCount);
        return response;
    }

    /**
     * 특정 게시물에 댓글을 추가.
     * @param id 게시물 ID.
     * @param content 댓글 내용.
     * @param session 현재 사용자 정보를 담고 있는 세션.
     * @return 게시물 목록 페이지로 리다이렉트.
     */
    @PostMapping("/posts/{id}/comment")
    public String addComment(@PathVariable Long id, @RequestParam String content, HttpSession session) {
        // 현재 로그인된 사용자 정보 가져오기
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("error", "댓글을 작성하려면 로그인이 필요합니다.");
            return "redirect:/posts";
        }

        // 게시물 찾기
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            session.setAttribute("error", "게시물이 존재하지 않습니다.");
            return "redirect:/posts";
        }

        // 댓글 생성 및 저장
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
        return "redirect:/";
    }

    /**
     * 게시물 삭제 요청 처리.
     * @param id 게시물 ID.
     * @param session 현재 사용자 정보를 담고 있는 세션.
     * @return 게시물 목록 페이지로 리다이렉트.
     */
    @Transactional
    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable Long id, HttpSession session) {
        // 현재 로그인된 사용자 정보 가져오기
        User user = (User) session.getAttribute("user");

        if (user == null) {
            session.setAttribute("error", "로그인이 필요합니다.");
            return "redirect:/";
        }

        // 게시물 찾기
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            session.setAttribute("error", "게시물이 존재하지 않습니다.");
            return "redirect:/";
        }

        // 작성자 확인
        if (!post.getUser().getId().equals(user.getId())) {
            session.setAttribute("error", "게시물을 삭제할 권한이 없습니다.");
            return "redirect:/";
        }

        // 좋아요 및 댓글 삭제
        likeRepository.deleteAllByPost(post);
        commentRepository.deleteAllByPost(post);

        // 게시물 삭제
        postRepository.delete(post);
        return "redirect:/";
    }

    /**
     * 댓글 삭제 요청 처리.
     * @param id 댓글 ID.
     * @param session 현재 사용자 정보를 담고 있는 세션.
     * @return 게시물 목록 페이지로 리다이렉트.
     */
    @Transactional
    @PostMapping("/comments/{id}/delete")
    public String deleteComment(@PathVariable Long id, HttpSession session) {
        // 현재 로그인된 사용자 정보 가져오기
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("error", "로그인이 필요합니다.");
            return "redirect:/";
        }

        // 댓글 찾기
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null && comment.getUser().getId().equals(user.getId())) {
            commentRepository.delete(comment);
        } else {
            session.setAttribute("error", "삭제 권한이 없습니다.");
        }

        return "redirect:/";
    }

    /**
     * 게시물 수정 요청 처리.
     * @param id 수정하려는 게시물의 ID.
     * @param content 수정된 내용.
     * @param session 현재 사용자 세션.
     * @return 게시물 페이지로 리다이렉트.
     */
    @PostMapping("/posts/{id}/edit")
    public String editPost(@PathVariable Long id, @RequestParam String content, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("error_" + id, "로그인이 필요합니다.");
            return "redirect:/";
        }

        Post post = postRepository.findById(id).orElse(null);
        if (post == null || !post.getUser().getId().equals(user.getId())) {
            session.setAttribute("error_" + id, "게시물을 수정할 권한이 없습니다.");
            return "redirect:/";
        }

        post.setContent(content);
        postRepository.save(post); // 수정된 내용 저장
        return "redirect:/";
    }

}
