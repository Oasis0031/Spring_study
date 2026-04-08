package com.app.threetier;

import com.app.threetier.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PostServiceTests {

    @Autowired
    private PostService postService;

    @Test
    void getPostTest() {
        log.info("post: {}", postService.getPost(1L));
    }
}
