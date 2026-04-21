package com.app.restful.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
<<<<<<< HEAD
@Schema(description = "게시글 수정 DTO")
=======
@Schema(description = "회원 정보 수정 DTO")
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
public class PostUpdateRequestDTO {
    @Schema(description = "게시글 제목", example = "수정된 게시글 제목", required = true)
    private String postTitle;
    @Schema(description = "게시글 내용", example = "수정된 게시글 내용", required = true)
    private String postContent;
}
