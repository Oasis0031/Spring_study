package com.app.restful.repository;


import com.app.restful.domain.vo.MemberVO;
import com.app.restful.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {

    private final MemberMapper memberMapper;
    // [수정] MemberVO는 데이터 객체이므로 @Repository에서 주입(Bean)받으면 안 됩니다. 삭제하세요.

    // 회원 추가
    public void save(MemberVO memberVO){
        memberMapper.insert(memberVO);
    }

    // 회원 중복 조회
    public int existMemberEmail(String memberEmail){
        return memberMapper.existedEmail(memberEmail);
    }

    // 회원 전체 조회
    public List<MemberVO> findAll(){
        return memberMapper.selectAll();
    }

    // [수정] 회원 단일 조회(ID)
    // 1. static을 제거해야 memberMapper에 접근할 수 있습니다.
    // 2. memberMapper.selectById(id)의 결과를 Optional로 감쌉니다.
    public Optional<MemberVO> findById(Long id){
        return Optional.ofNullable(memberMapper.selectById(id));
    }

    // 회원 단일 조회(Email, Password)
    public Optional<MemberVO> findByMemberEmailAndMemberPassword(MemberVO memberVO){
        return Optional.ofNullable(memberMapper.selectByMemberEmailAndMemberPassword(memberVO));
    }

    // [수정] 회원 수정
    // 서비스 단에서 성공 여부를 확인할 수 있도록 int(영향받은 행 수)를 반환하는 것이 좋습니다.
    public int updateMember(MemberVO updateRequestDTO) {
        // DTO를 VO로 변환 후 처리하는 로직이 들어갑니다.
        // MemberVO memberVO = MemberVO.from(updateRequestDTO);
        // return memberDAO.updateMember(memberVO);
        return 0;
    }

    // 회원 삭제
    public void delete(Long id){
        memberMapper.delete(id);
    }
}