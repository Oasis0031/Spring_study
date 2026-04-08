package com.app.ncs.controller;

import com.app.ncs.domain.vo.MemberVO;
import com.app.ncs.mapper.MemberMapper;
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

    //요청 건내서 응답 받기
    @GetMapping("join")
    public void goToJoin(MemberVO memberVO) {;
    }

    //이동
    @PostMapping("join")
        public RedirectView join(MemberVO memberVO) {
        memberMapper.insert(memberVO);
        return new RedirectView("/members/login");
    }


}
