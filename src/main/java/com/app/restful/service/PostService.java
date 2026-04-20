package com.app.restful.service;

import com.app.restful.domain.dto.PostCreateRequestDTO;
import com.app.restful.domain.dto.PostDTO;
import com.app.restful.domain.dto.PostUpdateRequestDTO;
<<<<<<< HEAD
=======
import com.app.restful.domain.vo.PostVO;
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6

import java.util.List;

public interface PostService {

<<<<<<< HEAD
    // 게시글 목록 조회 서비스
    public List<PostDTO> getPostList(String order);

    // 게시글 상세보기 조회 서비스
    // 서비스 단에서는 Optional이 벗겨진 상태로 리턴된다.
    public PostDTO getPostDetail(Long id);

    // 게시글 작성 서비스
    public void createPost(PostCreateRequestDTO postCreateRequestDTO, Long memberId);;

    // 게시글 수정 서비스
    public void modifyPost(PostUpdateRequestDTO postUpdateRequestDTO, Long id);

    // 게시글 삭제 서비스
    public void remove(Long id);

    // 게시글 삭제(탈퇴시) 서비스
    public void removeByMemberId(Long memberId);
=======
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

>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
}
