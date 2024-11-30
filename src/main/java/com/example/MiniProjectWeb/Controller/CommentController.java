package com.example.MiniProjectWeb.Controller;

import com.example.MiniProjectWeb.Entity.Comment;
import com.example.MiniProjectWeb.Entity.User;
import com.example.MiniProjectWeb.Repository.CommentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    // 댓글 수정
    @Transactional
    @PostMapping("/comments/{id}/edit")
    public String editComment(@PathVariable Long id, @RequestParam String content, HttpSession session) {
        // 로그인된 사용자 확인
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("error", "로그인이 필요합니다.");
            return "redirect:/";
        }

        // 댓글 조회
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null) {
            session.setAttribute("error", "댓글을 찾을 수 없습니다.");
            return "redirect:/";
        }

        // 수정 권한 확인
        if (!comment.getUser().getId().equals(user.getId())) {
            session.setAttribute("error", "댓글을 수정할 권한이 없습니다.");
            return "redirect:/";
        }

        // 댓글 내용 수정
        comment.setContent(content);
        commentRepository.save(comment);

        return "redirect:/";
    }
}
