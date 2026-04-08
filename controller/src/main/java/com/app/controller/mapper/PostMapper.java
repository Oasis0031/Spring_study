package com.app.controller.mapper;

import com.app.controller.domain.dto.PostDTO;
import com.app.controller.domain.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    public List<PostDTO> selectAll();
}
