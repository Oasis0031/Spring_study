package com.app.controller;

import com.app.controller.mapper.PostMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PostMapperTests {

    @Autowired
    private PostMapper postMapper;

    @Test
    public void postSelectAll() {
        log.info("posts: {}", postMapper.selectAll());
    }
}
