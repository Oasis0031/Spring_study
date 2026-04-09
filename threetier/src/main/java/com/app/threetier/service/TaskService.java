package com.app.threetier.service;

import com.app.threetier.domain.vo.TaskVO;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {

    public TaskVO getTotalAndAverage(TaskVO taskVO);

}
