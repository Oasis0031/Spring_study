package com.app.threetier.mapper;

import com.app.threetier.domain.vo.TaskVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper {
    public TaskVO selectTotalAndAverage(TaskVO taskVO);
}
