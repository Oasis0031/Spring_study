package com.app.restful.repository;

import com.app.restful.domain.dto.PostDTO;
import com.app.restful.domain.vo.PostVO;
import com.app.restful.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostDAO {
<<<<<<< HEAD

    private final PostMapper postMapper;

    // 게시글 목록 조회
    public List<PostDTO> getPosts(Map<String, String> orders) {
        return postMapper.selectAll(orders);
    }

    // 게시글 상세보기 조회
    public Optional<PostDTO> getPost(Long id) {
        return Optional.ofNullable(postMapper.select(id));
    }

    // 게시글 작성
    public void save(PostVO postVO) {
=======
    private final PostMapper postMapper;

    // 게시글 전체 목록 조회
    public List<PostDTO> getPosts(Map<String, String> orders){
        return postMapper.selectAll(orders);
    }

    // 게시글 단일 조회
    public Optional<PostDTO> getPost(Long id){
        return Optional.ofNullable(postMapper.select(id));
    }

    // 게시글 추가
    public void save(PostVO postVO){
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
        postMapper.insert(postVO);
    }

    // 게시글 수정
<<<<<<< HEAD
    public void update(PostVO postVO) {
=======
    public void update(PostVO postVO){
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
        postMapper.update(postVO);
    }

    // 게시글 삭제
<<<<<<< HEAD
    public void delete(Long id) {
        postMapper.delete(id);
    }

    // 게시글 삭제(탈퇴시)
    public void deleteByMemberId(Long memberId) {
        postMapper.deleteByMemberId(memberId);
    }
















=======
    public void delete(Long id){
        postMapper.delete(id);
    }

    // 탈퇴시 게시글 삭제
    public void deleteByMemberId(Long memberId){
        postMapper.deleteByMemberId(memberId);
    }
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
}
