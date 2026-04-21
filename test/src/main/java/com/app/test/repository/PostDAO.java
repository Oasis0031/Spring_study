package com.app.test.repository;


import com.app.test.domain.dto.PostDTO;
import com.app.test.domain.vo.PostVO;
import com.app.test.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostDAO {
    private final PostMapper postMapper;

    // 수정: List<PostVO>를 지우고 List<PostDTO>로 통일
    public List<PostDTO> getPosts(Map<String, String> orders) {
        return postMapper.select(orders);
    }

    // 유지: 단일 조회는 DTO로 반환
    public Optional<PostDTO> getPost(Long id) {
        return Optional.ofNullable(postMapper.selectById(id));
    }

    // 유지: 삽입/수정은 테이블 구조인 VO를 사용
    public void save(PostVO postVO) {
        postMapper.insert(postVO);
    }

    public void update(PostVO postVO) {
        postMapper.update(postVO);
    }

    public void delete(Long id) {
        postMapper.delete(id);
    }

    public void deleteByMemberId(Long memberId) {
        postMapper.deleteByMemberId(memberId);
    }
}