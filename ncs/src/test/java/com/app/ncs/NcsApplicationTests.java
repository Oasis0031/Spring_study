package com.app.ncs;

import com.app.ncs.domain.vo.MemberVO;
import com.app.ncs.mapper.MemberMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NcsApplicationTests {

    @Test
    void contextLoads() {}

    @Test
    void memberMapperTest() {
        MemberMapper memberMapper = Mockito.mock(MemberMapper.class);
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("1234test@gmail.com");
        memberVO.setMemberPassword("1234test");
        memberVO.setMemberName("고길동");
        memberMapper.insert(memberVO);

    }
}
