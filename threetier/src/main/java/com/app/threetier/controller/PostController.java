package com.app.threetier.controller;


import com.app.threetier.mapper.PostMapper;
import com.app.threetier.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts/*")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/list")
    public void goToPostList(Model model){
        model.addAttribute("posts", postService.getPosts());
    }

    @GetMapping("/read")
    public void goToRead(Long id, Model model){
        model.addAttribute("post", postService.getPost(id));
    }
}
