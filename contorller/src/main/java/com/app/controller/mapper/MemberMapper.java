package com.app.controller.mapper;

import com.app.controller.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {
    public void insert(MemberVO memberVO);
    public Optional<MemberVO> selectByEmail(MemberVO memberVO);
    public void selectByMemberEmailAndMemberPassword(MemberVO memberVO);
    public void delete(Long id);
}
