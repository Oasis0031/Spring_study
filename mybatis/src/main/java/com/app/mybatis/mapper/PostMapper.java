package com.app.mybatis.mapper;

import com.app.mybatis.domain.dto.PostCountDTO;
import com.app.mybatis.domain.dto.PostDTO;
import com.app.mybatis.domain.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface PostMapper {
    public void insert(PostVO postVO);
    public List<PostDTO> selectAll(Long id);
    public Optional<PostDTO> select(Long id);
    public void update(PostVO postVO);
    public void delete(Long id);
    public void updatePostReadCount(Long id);
    public List<PostDTO> selectAllWithOrder(HashMap<String, Object> orders);
     public PostCountDTO selectPostCountByMemberId(int limit);
    public PostCountDTO selectTotalPostCountAndTotalPageCount(int limit);
}