package com.app.restful.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PostUpdateDTO {
    private String postTitle;
    private String postContent;
}
