package com.app.restful.domain.vo;

import com.app.restful.domain.dto.PostUpdateDTO;
import com.app.restful.domain.dto.PostWriteRequestDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PostVO {
    private Long id;
    private String postTitle;
    private String postContent;
    private String memberName;
    private Long postReadCount;

    // 라이트 정적 팩토리 메서드
    public static PostVO from(PostWriteRequestDTO postWriteRequestDTO) {
        PostVO postVO = new PostVO();
        postVO.setPostTitle(postWriteRequestDTO.getPostTitle());
        postVO.setPostContent(postWriteRequestDTO.getPostContent());
        postVO.setMemberName(postVO.getMemberName());
        postVO.setPostReadCount(postVO.getPostReadCount());
        return postVO;
    }

    // 업데이트 정적 팩토리 메서드
    public static PostVO from(PostUpdateDTO postUpdateDTO) {
        PostVO postVO = new PostVO();
        postVO.setPostTitle(postUpdateDTO.getPostTitle());
        postVO.setPostContent(postUpdateDTO.getPostContent());
        return postVO;
    }

}
