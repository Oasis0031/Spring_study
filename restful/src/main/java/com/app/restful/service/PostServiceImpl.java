package com.app.restful.service;

import com.app.restful.domain.dto.PostCreateRequestDTO;
import com.app.restful.domain.dto.PostDTO;
import com.app.restful.domain.dto.PostUpdateRequestDTO;
import com.app.restful.domain.vo.PostVO;
import com.app.restful.exception.PostException;
import com.app.restful.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;

    // 1. 게시글 전체 조회 (정렬 조건 포함)
    @Override
    public List<PostDTO> getPostList(String order) {
        Map<String, String> orders = new HashMap<>();
        orders.put("order", order);
        return postDAO.getPosts(orders);
    }

    // 2. 게시글 상세 조회
    @Override
    public PostDTO getPostDetail(Long id) {
        return postDAO.getPost(id)
                .orElseThrow(() -> new PostException("게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
    }

    // 3. 게시글 작성
    @Override
    public void createPost(PostCreateRequestDTO postCreateRequestDTO, Long memberId) {
        // PostVO.from 메서드가 PostCreateRequestDTO를 인자로 받도록 설계되어 있어야 합니다.
        PostVO postVO = PostVO.from(postCreateRequestDTO);
        postVO.setMemberId(memberId); // 작성자 ID 설정
        postDAO.save(postVO);
    }

    // 4. 게시글 수정
    @Override
    public void modifyPost(PostUpdateRequestDTO postUpdateRequestDTO, Long id) {
        PostVO postVO = PostVO.from(postUpdateRequestDTO);
        postVO.setId(id); // 수정할 게시글 번호 설정
        postDAO.update(postVO);
    }

    // 5. 게시글 삭제
    @Override
    public void remove(Long id) {
        postDAO.delete(id);
    }

    // 6. 회원 탈퇴 시 해당 회원의 게시글 삭제
    @Override
    public void removeByMemberId(Long memberId) {
        postDAO.deleteByMemberId(memberId);
    }
}