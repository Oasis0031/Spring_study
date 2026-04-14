package com.app.restful.service;

import com.app.restful.domain.dto.MemberJoinRequestDTO;
import com.app.restful.domain.dto.MemberResponseDTO;
import com.app.restful.domain.dto.MemberUpdateRequestDTO;
import com.app.restful.domain.vo.MemberVO;
import com.app.restful.exception.MemberException;
import com.app.restful.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;

    @Override
    public void join(MemberJoinRequestDTO memberJoinRequestDTO) {
        this.checkMemberEmailDuplicate(memberJoinRequestDTO.getMemberEmail());
        memberDAO.save(MemberVO.from(memberJoinRequestDTO));
    }

    @Override
    public void checkMemberEmailDuplicate(String memberEmail) {
        if(memberDAO.existMemberEmail(memberEmail) > 0){
            throw new MemberException("이미 사용 중인 이메일입니다.");
        }
    }

    @Override
    public void updateMember(MemberUpdateRequestDTO memberUpdateRequestDTO) {
        // 1. DTO를 VO로 변환
        MemberVO memberVO = MemberVO.from(memberUpdateRequestDTO);

        // 2. DAO를 통해 수정 실행 (성공 시 1, 실패 시 0 반환 가정)
        int result = memberDAO.updateMember(memberVO);

        if (result == 0) {
            throw new MemberException("수정할 회원 정보를 찾을 수 없습니다.");
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
                .orElseThrow(() -> new MemberException("아이디 또는 비밀번호를 확인하세요."));
    }

    @Override
    public MemberResponseDTO getMemberInfo(Long id) {
        return memberDAO.findById(id)
                .map(MemberResponseDTO::from)
                .orElseThrow(() -> new MemberException("회원을 찾을 수 없습니다."));
    }

    @Override
    public void deleteMember(Long id) {
        memberDAO.delete(id);
    }
}