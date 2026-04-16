package com.app.restful.mapper;

import com.app.restful.domain.dto.PostDTO;
import com.app.restful.domain.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {

    // 1. 게시글 작성: 테이블에 데이터를 넣을 때는 VO를 사용합니다.
    public void insert(PostVO postVO);

    // 2. 게시글 전체 조회: XML에서 resultType="PostDTO"이므로 DTO 리스트를 반환해야 합니다.
    // (JOIN을 통해 가져오는 회원 이름 등을 담기 위함)
    public List<PostDTO> select(Map<String, String> orders);

    // 3. 게시글 상세보기: 특정 ID로 조회 시에도 회원 정보를 포함한 DTO를 반환합니다.
    public PostDTO selectById(Long id);

    // 4. 게시글 수정: 파라미터로 수정할 내용이 담긴 VO 객체를 전달해야 합니다.
    // (기존 코드의 'Long id' 혹은 'PostVO id' 부분을 수정)
    public void update(PostVO postVO);

    // 5. 게시글 삭제
    public void delete(Long id);

    // 6. 회원 탈퇴 시 게시글 삭제
    public void deleteByMemberId(Long memberId);
}