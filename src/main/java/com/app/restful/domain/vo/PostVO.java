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
<<<<<<< HEAD
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
=======
    private Long PostReadCount;
    private Long memberId;

    // 정적 팩토리 메서드
    public static PostVO from(PostUpdateRequestDTO postUpdateRequestDTO){
        PostVO postVO = new PostVO();
        postVO.setPostTitle(postUpdateRequestDTO.getPostTitle());
        postVO.setPostContent(postUpdateRequestDTO.getPostContent());
        return postVO;
    }

    public static PostVO from(PostCreateRequestDTO postCreateRequestDTO){
        PostVO postVO = new PostVO();
        postVO.setPostTitle(postCreateRequestDTO.getPostTitle());
        postVO.setPostContent(postCreateRequestDTO.getPostContent());
        return postVO;
    }
}
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
