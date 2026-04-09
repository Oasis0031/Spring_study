package com.app.threetier;

import com.app.threetier.domain.vo.TaskVO;
import com.app.threetier.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class TaskServiceTests {

    @Autowired
    private TaskService taskService;

    @Test
    public void taskServiceTest1(){
        // 1. 데이터 세팅
        TaskVO taskVO = new TaskVO();
        taskVO.setTaskKor(30);
        taskVO.setTaskEng(30);
        taskVO.setTaskMath(60);

        // 2. 서비스 호출 (결과가 담긴 VO를 다시 받음)
        TaskVO task = taskService.getTotalAndAverage(taskVO);

        // 3. 로그 출력 (String.valueOf 대신 플레이스홀더 {}를 쓰는 것이 더 편합니다)
        log.info("총점: {}", task.getTotal());
        log.info("평균: {}", task.getAverage());

        // 또는 객체 전체 정보를 보고 싶다면 (Lombok @Data 사용 시)
        log.info("전체 결과: {}", task);
    }

}
