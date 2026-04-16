package com.app.restful.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "회원 가입 데이터")
public class MemberJoinRequestDTO {
    @Schema(description = "회원 이메일", example = "test123@gmail.com", required = true)
    private String memberEmail;
    @Schema(description = "회원 비밀번호", example = "test123", required = true)
    private String memberPassword;
    @Schema(description = "회원 이름", example = "야마다")
    private String memberName;
}
