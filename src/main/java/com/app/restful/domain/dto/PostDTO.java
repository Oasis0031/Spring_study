package com.app.restful.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "게시글 DTO")
public class PostDTO {
    @Schema(description = "게시글 번호", example = "1", required = true)
    private Long id;
    @Schema(description = "게시글 제목", example = "게시글 제목1", required = true)
    private String postTitle;
    @Schema(description = "게시글 내용", example = "게시글 내용", required = true)
    private String postContent;
    @Schema(description = "회원 번호", example = "1", required = true)
    private Long memberId;
    @Schema(description = "게시글 조회수", example = "150")
    private Long postReadCount;
    @Schema(description = "회원 이메일", example = "test123@gmail.com", required = true)
    private String memberEmail;
    @Schema(description = "회원 이름", example = "홍길동")
    private String memberName;
}
