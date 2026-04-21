package com.app.test.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "게시글 DTO")
public class PostDTO {

    @Schema(description = "게시글 번호", example = "1",required = true)
    private Long id;
    @Schema(description = "게시글 제목", example = "제목", required = true)
    private String postTitle;
    @Schema(description = "게시글 내용", example = "내용", required = true)
    private String postContent;
    @Schema(description = "회원 번호", example = "1", required = true)
    private Long memberId;
    @Schema(description = "게시글 조회수", example = "1")
    private Long postReadCount;
    @Schema(description = "회원 이메일", example = "gonk123@gmail.com")
    private String memberEmail;
    @Schema(description = "회원 이름", example = "홍민호")
    private String memberName;
}
