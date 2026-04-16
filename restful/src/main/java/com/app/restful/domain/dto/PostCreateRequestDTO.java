package com.app.restful.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@ToString
@Schema(description = "게시글 생성 DTO")
public class PostCreateRequestDTO {
    @Schema(description = "게시글 제목", example = "게시글 제목 1", required = true)
    private String postTitle;
    @Schema(description = "게시글 내용", example = "게시글 내용 1", required = true)
    private String postContent;

    // 만약 작성자 ID를 JSON body로 직접 받는 구조라면 아래 주석을 해제하세요.
    // 현재 PostServiceImpl에서는 파라미터로 따로 받고 있습니다.
    // private Long memberId;
}