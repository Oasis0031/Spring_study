package com.app.test.service;


import com.app.test.domain.dto.MemberJoinRequestDTO;
import com.app.test.domain.dto.MemberResponseDTO;
import com.app.test.domain.dto.MemberUpdateRequestDTO;
import com.app.test.domain.vo.MemberVO;
import com.app.test.exception.MemberException;
import com.app.test.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;
    private PostService postService;

    @Override
    public MemberResponseDTO join(MemberJoinRequestDTO memberJoinRequestDTO) {
            this.checkMemberEmailDuplicate(memberJoinRequestDTO.getMemberEmail());
            memberDAO.save(MemberVO.from(memberJoinRequestDTO));
        return null;
    }

    @Override
    public MemberResponseDTO login(MemberVO memberVO) {

        return memberDAO.findByMemberEmailAndMemberPassword(memberVO)
                .map(MemberResponseDTO::from)
                .orElseThrow(() -> new MemberException("아이디 또는 비밀번호를 확인하세요", HttpStatus.UNAUTHORIZED));
    }

    @Override
    public List<MemberResponseDTO> getMemberInfoList() {

        return memberDAO.findById(id)
                .map(MemberResponseDTO)
    }

    @Override
    public MemberResponseDTO getMemberInfo(Long id) {
        return null;
    }

    @Override
    public void checkMemberEmailDuplicate(String memberEmail) {
        if (memberDAO.existMemberEmail(memberEmail) > 0) {
            throw new MemberException("중복된 이메일", HttpStatus.CONFLICT);
        }
    }

    @Override
    public void updateMember(MemberUpdateRequestDTO memberUpdateRequestDTO) {

    }

    @Override
    public void withdraw(Long id) {

    }
}