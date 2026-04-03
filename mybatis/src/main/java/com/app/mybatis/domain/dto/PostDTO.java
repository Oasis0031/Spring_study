package com.app.mybatis.domain.dto;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PostDTO {
    private Long id;
    private String postTitle;
    private String postContent;
    private Long memberId;
    private Long postReadCount;
    private String memberEmail;
    private String memberName;
    private Long postLikeCount;
    private Boolean isLike;
}
