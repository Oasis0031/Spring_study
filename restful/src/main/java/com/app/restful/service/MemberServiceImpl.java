package com.app.restful.service;

import com.app.restful.domain.dto.MemberJoinRequestDTO;
import com.app.restful.domain.dto.MemberResponseDTO;
import com.app.restful.domain.dto.MemberUpdateRequestDTO;
import com.app.restful.domain.vo.MemberVO;
import com.app.restful.exception.MemberException;
import com.app.restful.repository.MemberDAO;
import com.app.restful.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;
    private final PostDAO postDAO;

    @Override
    public MemberResponseDTO join(MemberJoinRequestDTO memberJoinRequestDTO) {
        // 1. 이메일 중복 여부 확인
        this.checkMemberEmailDuplicate(memberJoinRequestDTO.getMemberEmail());
        // 2. DTO -> VO 변환 후 저장
        memberDAO.save(MemberVO.from(memberJoinRequestDTO));
        return null;
    }

    @Override
    public void checkMemberEmailDuplicate(String memberEmail) {
        if (memberDAO.existMemberEmail(memberEmail) > 0) {
            throw new MemberException("이메일이 존재합니다.", HttpStatus.CONFLICT);
        }
    }

    @Override
    public List<MemberResponseDTO> getMemberInfoList() {
        return memberDAO.findAll().stream()
                .map(MemberResponseDTO::from)
                .toList();
    }

    @Override
    public MemberResponseDTO login(MemberVO memberVO) {
        return memberDAO.findByMemberEmailAndMemberPassword(memberVO)
                .map(MemberResponseDTO::from)
                // 수정: orElseThrow 내부에서는 throw 문을 작성하는 것이 아니라 예외 객체만 생성합니다.
                .orElseThrow(() -> new MemberException("아이디 또는 비밀번호를 확인하세요.", HttpStatus.UNAUTHORIZED));
    }

    @Override
    public MemberResponseDTO getMemberInfo(Long id) {
        return memberDAO.findById(id)
                .map(MemberResponseDTO::from)
                // 수정: 불필요한 중괄호와 throw 키워드 중복 제거
                .orElseThrow(() -> new MemberException("회원을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST));
    }

    @Override
    public void updateMember(MemberUpdateRequestDTO memberUpdateRequestDTO) {
        // MemberVO.from(memberUpdateRequestDTO)의 반환 타입이 MemberVO인지 확인 필수!
        memberDAO.update(MemberVO.from(memberUpdateRequestDTO));
    }

    @Override
    public void withdraw(Long id) {
        // 1. 참조하고 있는 게시글부터 삭제 (작성자 ID 기준)
        // postDAO의 메서드명이 deleteByMemberId인지 확인하세요.
        postDAO.deleteByMemberId(id);

        // 2. 회원 삭제
        memberDAO.delete(id);
    }
}