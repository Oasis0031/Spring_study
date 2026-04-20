package com.app.restful.service;

import com.app.restful.domain.dto.PostCreateRequestDTO;
import com.app.restful.domain.dto.PostDTO;
import com.app.restful.domain.dto.PostUpdateRequestDTO;
import com.app.restful.domain.vo.PostVO;

import java.util.List;

public interface PostService {

    // 게시글 전체 목록 조회
    public List<PostDTO> getPostList(String order);
    // 게시글 상세 보기
    public PostDTO getPostDetail(Long id);
    // 게시글 작성
    public void createPost(PostCreateRequestDTO postCreateRequestDTO, Long memberId);
    // 게시글 수정
    public void modifyPost(PostUpdateRequestDTO postUpdateRequestDTO, Long id);
    // 게시글 삭제
    public void remove(Long id);
    // 탈퇴시 게시글 삭제
    public void removeByMemberId(Long memberId);

}
