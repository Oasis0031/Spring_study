package com.app.controller.controller;

import com.app.controller.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ex/*") //프론트 컨트롤러처럼 사용
public class ExampleController {

    private final MemberVO memberVO;

    public ExampleController(MemberVO memberVO) {
        this.memberVO = memberVO;
    }

    // Get 방식, html도 Get으로 변경
//
//    @GetMapping("/ex01")
//    public String ex01(String name,int age ){
//        log.info("이름: {}, 나이: {}, 만 나이: {}", name, age, age- 1);
//        return "ex01";
//    }
//
    // Post 방식
    @GetMapping("/ex01")
    public String ex01() {
        log.info("ex01 get 응답");
        return "ex01";
    }

    @PostMapping("/ex01")
    public void ex01(String name, int age) {
       log.info("ex01 응답 완료");
//        String name = "";
//        int age = 0;
       // GET 방식으로 데이터를 어떻게 보내야 하는가?
       // 이름과 나이를 요청보내고
       // 이름과 나이, 만나이를 로그에 출력하기
       log.info("이름: {}, 나이: {}, 만 나이: {}", name, age, age- 1);
       // ex01.html
       // ex01 -> templates/ex01.html
       // ex/ex/ex01 -> templates/ex/ex/ex01.html
       // 응답되는 페이지의 파일 경로
    }

    @GetMapping("/ex02")
    public String ex02(String name, Model model) {
        model.addAttribute("name", name);
        return "ex02";
    }

    @GetMapping("/ex03")
    public String ex03Get(Model model) {
        List<String> names = new ArrayList<>(Arrays.asList("홍길동", "장보고", "이순신"));
        model.addAttribute("names", names);
        return "ex03";
    }

    // ModelAttribute : 반드시 쿼리 스트링 값을 전달해야한다.
    @GetMapping("/ex04")
    public String ex04(@ModelAttribute("name") String name) {
        return "ex04";
    }

    // 이름, 취미를 받고 화면에 이름: 000 취미: 000으로 출력하기

    @GetMapping("/ex05")
    public String ex05(
            @ModelAttribute("name") String name,
            @ModelAttribute("hobby") String hobby
    ){
        return "ex05";
    }

    @GetMapping("ex06")
    public String goToEx06(){
        return "ex06";
    }

    @GetMapping("/ex06-Complete")
    public String ex06Complete(
            @ModelAttribute("memberName") String memberName
    ){
        return "ex06-Complete";
    }


    // 회원가입 완료 후
    // 페이지에 000님 환영합니다 출력
    @PostMapping("/ex06")
    public String ex06(MemberVO memberVO){
        log.info("응답 들어옴");
        log.info("memberVO: {}", memberVO);
//        return "redirect:/ex/ex06-Complete?memberName=" + memberVO.getMemberName();
        return "redirect:/ex/ex06-Complete";
    }

}
