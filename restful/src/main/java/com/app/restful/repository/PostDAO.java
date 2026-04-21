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
        postMapper.insert(postVO);
    }

    // 게시글 수정
    public void update(PostVO postVO){
        postMapper.update(postVO);
    }

    // 게시글 삭제
    public void delete(Long id){
        postMapper.delete(id);
    }

    // 탈퇴시 게시글 삭제
    public void deleteByMemberId(Long memberId){
        postMapper.deleteByMemberId(memberId);
    }
}
