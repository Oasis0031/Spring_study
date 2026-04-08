package com.app.ncs.mapper;

import com.app.ncs.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MemberMapper {
    public void insert(MemberVO memberVO);
}
