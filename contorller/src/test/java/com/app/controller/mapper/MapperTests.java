package com.app.controller.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MapperTests {

    @Autowired
    private TimeMapper timeMapper;

    @Test
    public void mapperTests() {
        log.info(timeMapper.getTime());
    }

}
