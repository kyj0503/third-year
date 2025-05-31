package com.webproject.minisns.controller;

import com.webproject.minisns.dto.PostDTO;
import com.webproject.minisns.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    private final PostService postService;

    public MainController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String showMainPage(Model model) {
        List<PostDTO> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "main";
    }
}
