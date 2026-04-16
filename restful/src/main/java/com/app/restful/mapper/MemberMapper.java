package com.app.restful.mapper;

import com.app.restful.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    // 회원 추가
    public void insert(MemberVO memberVO);

    // 회원 이메일 유무 조회
    public int existMemberEmail(String memberEmail);

    // 회원 전체 조회
    public List<MemberVO> selectAll();

    // 회원 단일 조회(ID)
    public MemberVO selectById(Long id);

    // 회원 단일 조회(Email, Password)
    public MemberVO selectByMemberEmailAndMemberPassword(MemberVO memberVO);

    // 회원 수정: 인터페이스 타입을 MemberVO로 수정 (XML의 parameterType과 일치)
    public void update(MemberVO memberVO);

    // 회원 삭제
    public void delete(Long id);
}