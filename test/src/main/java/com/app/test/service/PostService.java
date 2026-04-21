package com.app.test.service;

import com.app.restful.domain.dto.PostCreateRequestDTO;
import com.app.restful.domain.dto.PostDTO;
import com.app.restful.domain.dto.PostUpdateRequestDTO;

import java.util.List;

public interface PostService {
    // 게시글 전체 조회 (정렬 조건 포함)
    List<PostDTO> getPostList(String order);

    // 게시글 상세 조회
    PostDTO getPostDetail(Long id);

    // 게시글 작성
    void createPost(PostCreateRequestDTO postCreateRequestDTO, Long memberId);

    // 게시글 수정
    void modifyPost(PostUpdateRequestDTO postUpdateRequestDTO, Long id);

    // 게시글 삭제
    void remove(Long id);

    // 회원 탈퇴 시 해당 회원의 게시글 삭제
    void removeByMemberId(Long memberId);
}