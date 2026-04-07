package com.app.controller.mapper;

import com.app.controller.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
public class MemberMapperTests {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void memberInsertTest(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("test888@gmail.com");
        memberVO.setMemberPassword("1234");
        memberVO.setMemberName("김영희");
        memberMapper.insert(memberVO);
    }

    @Test
    public void selectByMemberEmailAndMemberPasswordTest(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("test123@gmail.com");
        memberVO.setMemberPassword("test123");
        Optional<MemberVO> foundMember = memberMapper.selectByMemberEmailAndMemberPassword(memberVO);
        log.info("foundMember : {}", foundMember);
    }

    @Test
    public void updateTest(){
        MemberVO memberVO = new MemberVO();
        memberVO.setId(24L);
        memberVO.setMemberEmail("test12333333@gmail.com");
        memberVO.setMemberPassword("test123");
        memberVO.setMemberName("홍길동");
        memberMapper.update(memberVO);
    }

    @Test
    public void deleteTest(){
        memberMapper.delete(24L);
    }
}
