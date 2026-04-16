package com.app.restful.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "게시글 정보")
public class PostResponseDTO {
    private Long id;
    private String postTitle;
    private String postContent;
    private Long postReadCount;
    private String memberName;

    // PostVO에는 memberName이 없으므로, 모든 정보가 있는 PostDTO를 받아서 변환합니다.
    public static PostResponseDTO from(PostDTO postDTO) {
        PostResponseDTO response = new PostResponseDTO();
        response.setId(postDTO.getId());
        response.setPostTitle(postDTO.getPostTitle());
        response.setPostContent(postDTO.getPostContent());
        response.setPostReadCount(postDTO.getPostReadCount());
        response.setMemberName(postDTO.getMemberName()); // 이제 에러가 나지 않습니다!
        return response;
    }
}