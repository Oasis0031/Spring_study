package com.app.ncs.controller;

import com.app.ncs.domain.vo.MemberVO;
import com.app.ncs.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequestMapping("/members/*")
@RequiredArgsConstructor
public class MemberController {

    private final MemberMapper memberMapper;
    private final HttpSession session;

    // 1. 횐갑

    //요청 건내서 응답 받기
    @GetMapping("join")
    public void goToJoin(MemberVO memberVO) {
        ;
    }

    //이동
    @PostMapping("join")
    public RedirectView join(MemberVO memberVO) {
        memberMapper.insert(memberVO);
        return new RedirectView("/members/login");
    }

    //2.로그인
    //포스트 매핑
    //회원 정보 받기
    //멤버가 있으면 마이페이지
    //아니면 로그인 리다렉
    @PostMapping("login")
    public RedirectView login(MemberVO memberVO, RedirectAttributes redirectAttributes) {
        Optional<MemberVO> isMember = memberMapper.selectByIdMemberEmailAndMemberPassword(memberVO);
        if (isMember.isPresent()) {
            session.setAttribute("member", isMember.get());
            return new RedirectView("/members/my-page");
        }

        redirectAttributes.addFlashAttribute("isLogin", false);
        return new RedirectView("/members/login");
    }

    @GetMapping("login")
    public void goToLogin(MemberVO memberVO) {
        // login.html 페이지를 띄워주는 역할
    }

    //2.5 my-page 이동
    @GetMapping("my-page")
    public void goToMyPage() {;}


    //3. 로그아웃
    //로그아웃 클릭 시 로그인 되어있는 상태라면 메인페이지 -> 로그인으로 리다이렉트
    @GetMapping("logout")
    public RedirectView logout(RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("isLogin", true);
        return new RedirectView("/members/login");
    }


    //4. 회원정보 갱신
    @GetMapping("update")
    public void goToUpdate(Model model){
        model.addAttribute("memberVO", session.getAttribute("member"));
    }

    @PostMapping("update")
    public RedirectView update(MemberVO memberVO) {
        memberMapper.update(memberVO);
        return new RedirectView("/members/my-page");
    }


    //5. 탈퇴
    @DeleteMapping("delete")
    public RedirectView delete(HttpSession session) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        memberMapper.delete(memberVO.getId());
        return new RedirectView("/members/login");
    }
}
