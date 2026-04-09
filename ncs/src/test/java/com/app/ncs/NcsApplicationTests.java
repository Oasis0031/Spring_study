package com.app.ncs;

import com.app.ncs.domain.vo.MemberVO;
import com.app.ncs.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class NcsApplicationTests {

    @Autowired
    private MemberMapper memberMapper;


    //로그인테스트
    @Test
    void memberMapperTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("1234test@gmail.com");
        memberVO.setMemberPassword("1234test");
        memberVO.setMemberName("고길동");
        memberMapper.insert(memberVO);

    }

    @Test
    void selectByIdMemberEmailAndMemberPassword(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("1234test@gmail.com");
        memberVO.setMemberPassword("1234test");
        memberMapper.selectByIdMemberEmailAndMemberPassword(memberVO);
}

    @Test
    void update(){
        MemberVO memberVO = new MemberVO();
        memberVO.setId(1L);
        memberVO.setMemberEmail("1111test@gmail.com");
        memberVO.setMemberPassword("1111test");
        memberVO.setMemberName("고희동");
        memberMapper.update(memberVO);
    }

    @Test
    void delete(){
        memberMapper.delete(1L);
    }
}

