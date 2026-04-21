package com.app.restful.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "게시판 생성 DTO")
public class PostCreateRequestDTO {
    @Schema(description = "게시글 제목", example = "게시글 제목1", required = true)
    private String postTitle;
    @Schema(description = "게시글 내용", example = "게시글 내용1", required = true)
    private String postContent;
<<<<<<< HEAD
//    나중에 토큰에서 추출
//    private Long memberId;
}
=======
    // 나중에 토큰에서 추출
    // private Long memberId;
}
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
