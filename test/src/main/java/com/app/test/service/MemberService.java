package com.app.test.service;


import com.app.test.domain.dto.MemberJoinRequestDTO;
import com.app.test.domain.dto.MemberResponseDTO;
import com.app.test.domain.dto.MemberUpdateRequestDTO;
import com.app.test.domain.vo.MemberVO;

import java.util.List;

public interface MemberService {
    // 회원가입
    public MemberResponseDTO join(MemberJoinRequestDTO memberJoinRequestDTO);

    // 로그인
    public MemberResponseDTO login(MemberVO memberVO);

    // 회원 전체 조회
    public List<MemberResponseDTO> getMemberInfoList();

    // 회원 정보 조회
    public MemberResponseDTO getMemberInfo(Long id);

    // 이메일 중복 확인
    public void checkMemberEmailDuplicate(String memberEmail);

    // 회원 정보 변경
    public void updateMember(MemberUpdateRequestDTO memberUpdateRequestDTO);

    // 회원 탈퇴
    public void withdraw(Long id);

}
