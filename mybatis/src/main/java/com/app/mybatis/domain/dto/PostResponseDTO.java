package com.app.mybatis.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class PostResponseDTO {
    private PostCountDTO postCountDTO;

    private Long totalPostCount;
    private Long totalPageCount;
    private List<PostDTO> posts;

}
