package com.app.controller.mapper;

import com.app.controller.domain.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {
    public void selectAll(PostVO postVO);
}
