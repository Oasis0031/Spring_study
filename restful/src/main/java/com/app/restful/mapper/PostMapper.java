package com.app.restful.mapper;


import com.app.restful.domain.dto.PostResponseDTO;
import com.app.restful.domain.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
    // 게시글 작성
    public void insert(PostVO postVO);
    // 게시글 전체 조회
    public List<PostVO> select();
    // 게시글 상세보기
    public Optional<PostVO> selectById(Long id);
    //게시글 수정
    public void update(PostVO id);
    //게시글 삭제
    public void delete(Long id);


}
