package com.app.aop;

import com.app.aop.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class sampleServiceTests {

    @Autowired
    private SampleService sampleService;

    @Test
    public void beforeTest(){
        log.info("result: {}", sampleService.doAdd("ㄱ", "2"));
    }


}
