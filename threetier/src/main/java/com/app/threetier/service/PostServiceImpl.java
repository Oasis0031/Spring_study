package com.app.threetier.service;

import com.app.threetier.domain.dto.PostDTO;
import com.app.threetier.exception.PostException;
import com.app.threetier.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//예외처리
// 트랜젝션 관리
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
       return postDAO.findByID(id).orElseThrow(() -> new PostException("post not found"));
    }

}
