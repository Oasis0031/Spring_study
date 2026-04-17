package com.app.restful.domain.vo;

import com.app.restful.domain.dto.PostCreateRequestDTO;
import com.app.restful.domain.dto.PostUpdateRequestDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PostVO {
    private Long id;
    private String postTitle;
    private String postContent;
    private Long memberId;
    private int postReadCount;

    // 게시글 작성용
    public static PostVO from(PostCreateRequestDTO dto) {
        PostVO postVO = new PostVO();
        postVO.setPostTitle(dto.getPostTitle());
        postVO.setPostContent(dto.getPostContent());
        return postVO;
    }

    // 게시글 수정용
    public static PostVO from(PostUpdateRequestDTO dto) {
        PostVO postVO = new PostVO();
        postVO.setPostTitle(dto.getPostTitle());
        postVO.setPostContent(dto.getPostContent());
        return postVO;
    }
}