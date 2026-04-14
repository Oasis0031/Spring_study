package com.app.restful.domain.vo;

import com.app.restful.domain.dto.MemberJoinRequestDTO;
import com.app.restful.domain.dto.MemberUpdateRequestDTO;
import lombok.Data;
import org.jetbrains.annotations.UnknownNullability;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class MemberVO implements Serializable {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    // 가입용 팩토리 메서드
    public static MemberVO from(@UnknownNullability MemberJoinRequestDTO joinDTO) {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail(joinDTO.getMemberEmail());
        memberVO.setMemberPassword(joinDTO.getMemberPassword());
        memberVO.setMemberName(joinDTO.getMemberName());
        return memberVO;
    }

    // 수정용 팩토리 메서드 (반환 타입: MemberVO)
    public static MemberVO from(@UnknownNullability MemberUpdateRequestDTO updateDTO) {
        MemberVO memberVO = new MemberVO();
        memberVO.setId(updateDTO.getId());
        // DTO에 memberEmail 필드가 있다면 아래 주석 해제
        // memberVO.setMemberEmail(updateDTO.getMemberEmail());
        memberVO.setMemberPassword(updateDTO.getMemberPassword());
        memberVO.setMemberName(updateDTO.getMemberName());
        return memberVO;
    }
}