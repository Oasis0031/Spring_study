package com.app.member.controller;

import com.app.member.domain.vo.MemberVO;
import com.app.member.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/members/*")
@RequiredArgsConstructor
public class MemberController {

    private final MemberMapper memberMapper;
    private final HttpSession session;

    //회원가입
    @GetMapping("join")
    public void goToJoin(MemberVO memberVO) {;}

    //값 받고, DB 저장하고, 응답
    @PostMapping("join")
    public RedirectView join(MemberVO memberVO) {
        memberMapper.insert(memberVO);
        return new RedirectView("/members/login");
    }

}
