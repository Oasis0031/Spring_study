package com.app.threetier.service;

import com.app.threetier.domain.vo.TaskVO;
import com.app.threetier.repository.TaskDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TaskServiceImpl implements TaskService {

    private final TaskDAO taskDAO;

    @Override
    public TaskVO getTotalAndAverage(TaskVO taskVO) {
        // 합계 계산
        int total = taskVO.getTaskKor() + taskVO.getTaskEng() + taskVO.getTaskMath();

        // 평균 계산 (소수점까지 고려한다면 3.0으로 나누고 결과는 double 권장)
        // 만약 TaskVO의 average가 int형이라면 그대로 두셔도 됩니다.
        int average = total / 3;

        // 전달받은 파라미터 taskVO에 결과 세팅
        taskVO.setTotal(total);
        taskVO.setAverage(average);

        return taskVO;
    }
}
