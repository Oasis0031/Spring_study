package com.app.restful.service;

import com.app.restful.domain.dto.MemberJoinRequestDTO;
import com.app.restful.domain.dto.MemberResponseDTO;
import com.app.restful.domain.dto.MemberUpdateRequestDTO;
import com.app.restful.domain.vo.MemberVO;

import java.util.List;

public interface MemberService {
    // 회원가입
    void join(MemberJoinRequestDTO memberJoinRequestDTO);

    // 로그인
    MemberResponseDTO login(MemberVO memberVO);

    // 이메일 중복 여부 확인
    void checkMemberEmailDuplicate(String memberEmail);

    // 회원 전체 조회
    List<MemberResponseDTO> getMemberInfoList();

    // 회원 정보 조회
    MemberResponseDTO getMemberInfo(Long id);

    // 회원 정보 변경 (리턴 타입을 void로 통일하여 Impl과 맞춤)
    void updateMember(MemberUpdateRequestDTO memberUpdateRequestDTO);

    // 회원 탈퇴
    void deleteMember(Long id);
}