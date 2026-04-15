package com.app.restful.service;

import com.app.restful.domain.dto.PostResponseDTO;
import com.app.restful.domain.dto.PostUpdateDTO;
import com.app.restful.domain.dto.PostWriteRequestDTO;
import com.app.restful.domain.vo.PostVO;

import java.util.List;

public interface PostService {

    //게시글 작성
    public void write(PostWriteRequestDTO postWriteRequestDTO);
    //게시글 전체 조회
    public List<PostResponseDTO> readAll();
    //게시글 단일 조회
    public PostResponseDTO readById(Long id);
    //게시글 수정
    public void update(PostUpdateDTO postUpdateDTO);
    //게시글 삭제
    public void delete(Long id);

}
