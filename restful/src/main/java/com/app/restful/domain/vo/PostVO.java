package com.app.restful.domain.vo;

import com.app.restful.domain.dto.PostCreateRequestDTO; // 명칭 확인 필요
import com.app.restful.domain.dto.PostUpdateRequestDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PostVO {
    private Long id;
    private String postTitle;
    private String postContent;
    private Long memberId;      // DB의 TBL_POST 테이블과 매핑 (FK)
    private Long postReadCount;

    // 작성(Insert)을 위한 정적 팩토리 메서드
    public static PostVO from(PostCreateRequestDTO postCreateRequestDTO) {
        PostVO postVO = new PostVO();
        postVO.setPostTitle(postCreateRequestDTO.getPostTitle());
        postVO.setPostContent(postCreateRequestDTO.getPostContent());
        // memberId는 ServiceImpl에서 별도로 setMemberId()로 넣어주므로 여기선 제목과 내용만 매핑합니다.
        return postVO;
    }

    // 수정(Update)을 위한 정적 팩토리 메서드
    public static PostVO from(PostUpdateRequestDTO postUpdateRequestDTO) {
        PostVO postVO = new PostVO();
        postVO.setPostTitle(postUpdateRequestDTO.getPostTitle());
        postVO.setPostContent(postUpdateRequestDTO.getPostContent());
        return postVO;
    }
}