package com.app.restful.service;

import com.app.restful.domain.dto.PostResponseDTO;
import com.app.restful.domain.dto.PostUpdateDTO;
import com.app.restful.domain.dto.PostWriteRequestDTO;
import com.app.restful.domain.vo.PostVO;
import com.app.restful.exception.PostException;
import com.app.restful.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;

    @Override
    public void write(PostWriteRequestDTO postWriteRequestDTO) {
        this.write(postWriteRequestDTO);
        postDAO.write(PostVO.from(postWriteRequestDTO));
    }


    //전체 조회
    @Override
    public List<PostResponseDTO> readAll() {
        return postDAO.readAll().stream().map(PostResponseDTO::from).toList();
    }

    //게시글 id 단일 조회
    @Override
    public PostResponseDTO readById(Long id) {
        return postDAO.readById(id)              // 1. 결과: Optional<PostVO>
                .map(PostResponseDTO::from)      // 2. 결과: Optional<PostResponseDTO> 로 변환
                .orElseThrow(() -> new PostException("게시글이 없습니다.")); // 3. 결과: PostResponseDTO (바구니 탈출)
    }

    @Override
    public void update(PostUpdateDTO postUpdateDTO) {
        postDAO.update(PostVO.from(postUpdateDTO));
    }

    @Override
    public void delete(Long id) {
        postDAO.delete(id);
    }
}
