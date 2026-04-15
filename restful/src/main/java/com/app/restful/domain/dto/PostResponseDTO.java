package com.app.restful.domain.dto;

import com.app.restful.domain.vo.PostVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "게시글 정보")
public class PostResponseDTO {
    @Schema(description = "게시글 번호", required = true, example = "1")
    private Long id;
    @Schema(description = "게시글 제목", required = true, example = "게시글명")
    private String postTitle;
    @Schema(description = "게시글 내용", required = true, example = "게시글 내용입니다.")
    private String postContent;
    @Schema(description = "조회수", example = "1")
    private Long postReadCount;
    @Schema(description = "작성자", required = true, example = "김아무개")
    private String memberName;

//      정적 팩토리 메서드
    public static PostResponseDTO from(PostVO postVO) {
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setId(postVO.getId());
        postResponseDTO.setPostTitle(postVO.getPostTitle());
        postResponseDTO.setPostContent(postVO.getPostContent());
        postResponseDTO.setPostReadCount(postVO.getPostReadCount());
        postResponseDTO.setMemberName(postVO.getMemberName());
        return postResponseDTO;
    }
}
