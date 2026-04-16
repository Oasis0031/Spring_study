package com.app.restful.domain.vo;

import com.app.restful.domain.dto.MemberJoinRequestDTO;
import com.app.restful.domain.dto.MemberUpdateRequestDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class MemberVO implements Serializable {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    // 회원가입 DTO -> VO 변환
    public static MemberVO from(MemberJoinRequestDTO memberJoinRequestDTO){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail(memberJoinRequestDTO.getMemberEmail());
        memberVO.setMemberPassword(memberJoinRequestDTO.getMemberPassword());
        memberVO.setMemberName(memberJoinRequestDTO.getMemberName());
        return memberVO;
    }

    // 수정 DTO -> VO 변환 (반환 타입을 MemberVO로 수정)
    public static MemberVO from(MemberUpdateRequestDTO memberUpdateRequestDTO){
        MemberVO memberVO = new MemberVO();
        memberVO.setId(memberUpdateRequestDTO.getId());
        memberVO.setMemberPassword(memberUpdateRequestDTO.getMemberPassword());
        memberVO.setMemberName(memberUpdateRequestDTO.getMemberName());
        return memberVO; // MemberVO 객체를 리턴하므로 반환 타입도 MemberVO여야 함
    }
}