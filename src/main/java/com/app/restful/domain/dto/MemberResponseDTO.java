package com.app.restful.domain.dto;

import com.app.restful.domain.vo.MemberVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
<<<<<<< HEAD
@Schema(description = "회원 응답 DTO")
=======
@Schema(description = "회원 정보 응답 DTO")
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
public class MemberResponseDTO {
    @Schema(description = "회원 번호", required = true, example = "1")
    private Long id;
    @Schema(description = "회원 이메일", required = true, example = "test123@gmail.com")
    private String memberEmail;
    @Schema(description = "회원 이름", example = "홍길동")
    private String memberName;

<<<<<<< HEAD
    //    정적 팩토리 메서드
=======
//    정적 팩토리 메서드
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
    public static MemberResponseDTO from(MemberVO memberVO) {
        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
        memberResponseDTO.setId(memberVO.getId());
        memberResponseDTO.setMemberEmail(memberVO.getMemberEmail());
        memberResponseDTO.setMemberName(memberVO.getMemberName());
        return memberResponseDTO;
    }
}
