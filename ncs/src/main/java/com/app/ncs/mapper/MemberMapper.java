package com.app.ncs.mapper;

import com.app.ncs.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;


@Mapper
public interface MemberMapper {
    public void insert(MemberVO memberVO);

    public Optional<MemberVO> selectByIdMemberEmailAndMemberPassword(MemberVO memberVO);

    public void update(MemberVO memberVO);

    public void delete(Long id);
}
