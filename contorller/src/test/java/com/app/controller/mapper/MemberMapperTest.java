package com.app.controller.mapper;

import com.app.controller.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
public class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void memberInsertTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("test8888@gmail.com");
        memberVO.setMemberPassword("123456");
        memberVO.setMemberName("김영희");
        memberMapper.insert(memberVO);
    }

    @Test
    public void selectByMemberEmailAndMemberPasswordTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("test8888");
        memberVO.setMemberPassword("123456");
        Optional<MemberVO> foundMember = memberMapper.update(memberVO);
        log.info("foundMember:{}", foundMember);
    }

        @Test
        public void deleteByIdTest() {
            memberMapper.delete(3L);
        }
}
