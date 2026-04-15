package com.app.restful.repository;

import com.app.restful.domain.vo.PostVO;
import com.app.restful.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostDAO {

    private final PostMapper postMapper;

    //게시글 작성
    public void write(PostVO postVO){
        postMapper.insert(postVO);
    };
    //게시글 전체 조회
    public List<PostVO> readAll(){
        return postMapper.select();
    };
    //게시글 단일 조회
    public Optional<PostVO> readById(Long id){
        return postMapper.selectById(id);
    };
    //게시글 수정
    public void update(PostVO postVO){
        postMapper.update(postVO);
    };
    //게시글 삭제
    public void delete(Long id){
        postMapper.delete(id);
    };

}
