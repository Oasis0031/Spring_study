package com.app.restful.mapper;

import com.app.restful.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    // 회원 추가
    public void insert(MemberVO memberVO);

    // 회원 전체 조회
    public List<MemberVO> selectAll();

    // 회원 단일 조회(ID)
    public MemberVO selectById(Long id);

    // 회원 단일 조회(Email, Password) - 로그인 등에 사용
    public MemberVO selectByMemberEmailAndMemberPassword(MemberVO memberVO);

    // [추가] 이메일 중복 조회 (존재하면 1, 없으면 0 반환)
    public int existedEmail(String memberEmail);

    // [수정] 회원 수정 (성공한 행의 개수 반환)
    public int update(MemberVO memberVO);

    // 회원 삭제
    public void delete(Long id);
}