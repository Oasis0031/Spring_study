package com.app.threetier.service;

import com.app.threetier.domain.dto.PostDTO;
import com.app.threetier.domain.vo.PostVO;
import com.app.threetier.exception.PostException;
import com.app.threetier.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 예외처리
// 트랜잭션 관리
// 메인 로직 작성
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {PostException.class, Exception.class})
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;

    @Override
    public List<PostDTO> getPosts() {
        return postDAO.findAll();
    }

    @Override
    public PostDTO getPost(Long id) {
        this.increaseReadCount(id);
        return postDAO.findById(id).orElseThrow(() -> new PostException("게시물을 찾을 수 없습니다."));
    }

    @Override
    public void updatePost(PostVO postVO) {
        postDAO.update(postVO);
    }

    @Override
    public void increaseReadCount(Long id) {
        postDAO.updateReadCount(id);
    }

    @Override
    public void deletePost(Long id) {
        postDAO.delete(id);
    }
}
