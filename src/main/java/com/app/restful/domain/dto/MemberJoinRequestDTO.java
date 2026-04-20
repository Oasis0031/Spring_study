package com.app.restful.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "회원 가입 DTO")
public class MemberJoinRequestDTO {
<<<<<<< HEAD
    @Schema(description = "회원 이메일", example = "test123@gmail.com", required = true)
=======
    @Schema(description = "회원 이메일", example = "test123@gamil.com", required = true)
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
    private String memberEmail;
    @Schema(description = "회원 비밀번호", example = "1234", required = true)
    private String memberPassword;
    @Schema(description = "회원 이름", example = "홍길동")
    private String memberName;
}
