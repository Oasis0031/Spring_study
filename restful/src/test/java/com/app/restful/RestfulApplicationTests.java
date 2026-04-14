package com.app.restful;

import com.app.restful.domain.dto.MemberJoinRequestDTO;
import com.app.restful.domain.dto.MemberUpdateRequestDTO;
import com.app.restful.domain.vo.MemberVO;
import com.app.restful.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestfulApplicationTests {

    @Autowired
    private MemberJoinRequestDTO memberJoinRequestDTO;
    @Autowired
    private MemberService memberService;

    @Test
    public void insertMember() {
        MemberJoinRequestDTO memberJoinRequestDTO = new MemberJoinRequestDTO();
        memberJoinRequestDTO.setMemberEmail("test174@gmail.com");
        memberJoinRequestDTO.setMemberPassword("test132");
        memberJoinRequestDTO.setMemberName("깅장훈");
        memberService.join(memberJoinRequestDTO);
    }

    @Test
    public void memberUpdateTest() {
        MemberUpdateRequestDTO memberUpdateRequestDTO = new MemberUpdateRequestDTO();
        memberUpdateRequestDTO.setId(1L);
        memberUpdateRequestDTO.setMemberPassword("123456");
        memberUpdateRequestDTO.setMemberName("고길동");
        memberService.updateMember(memberUpdateRequestDTO);
    }
}
