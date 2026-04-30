package com.app.oauth.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AwsS3UtilTests {

    @Autowired
    private AwsS3Util awsS3Util;

    @Test
    public void getPathTests(){
        log.info(awsS3Util.getPath());
    }

    @Test
    public void displayTest(){
        log.info("{}", awsS3Util.display("2026/04/30/8fd282bc-f7ed-4fcf-9060-591e4eeb8f4b_cat.jpg"));
    }

}
