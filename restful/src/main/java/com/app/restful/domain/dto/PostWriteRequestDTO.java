package com.app.restful.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PostWriteRequestDTO {
    private Long id;
    private String postTitle;
    private String postContent;
    private String memberName;
    private Long postReadCount;
}
