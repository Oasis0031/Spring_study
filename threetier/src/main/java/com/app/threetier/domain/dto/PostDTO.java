package com.app.threetier.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PostDTO {
    private Long id;
    private String postTitle;
    private String postContent;
    private Long memberId;
    private Long PostReadCount;
    private String MemberEmail;
    private String MemberName;
}
