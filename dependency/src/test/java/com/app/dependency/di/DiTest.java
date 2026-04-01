package com.app.dependency.di;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // test를 위한 클래스
@Slf4j //Log에 결과를 출력
public class DiTest {

    // Test에서는 생성자 주입을 받을 수 없다.
    // 필드 주입
    @Autowired
    private Coding coding;

    @Autowired
    private Food food;

    @Autowired
    private Login login;

 ;   @Test
    public void codingTest(){

        //문자열만 출력 가능
        log.info("codingTest");
        log.info("coding: {}", coding.getComputer());
    }

    @Test
    public void FoodTest(){
        log.info("FoodTest");
        log.info("food {}", food.getKnife());
    }

    @Test
    public void loginTest(){
        log.info("loginTest");
        log.info("login: {}", login.getMember());
    }


}
