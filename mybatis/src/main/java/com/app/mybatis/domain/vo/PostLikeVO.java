package com.app.mybatis.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PostLikeVO {
    private Long id;
    private Long memberId;
    private Long postId;
}
