package com.app.controller.controller;

import com.app.controller.domain.vo.PostVO;
import com.app.controller.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/posts/*")
@RequiredArgsConstructor
public class PostController {

    private final PostMapper postMapper;


    @GetMapping("list")
    public void goToList(PostVO postVO){;}

    @PostMapping("list")
    public ModelAndView goToPostList(PostVO postVO){
        postMapper.selectAll(postVO);
        return new ModelAndView("/posts/list");
    }


}
