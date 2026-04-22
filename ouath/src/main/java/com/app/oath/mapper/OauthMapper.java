package com.app.oath.mapper;

import com.app.oath.domain.vo.OauthMemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OauthMapper {
    public OauthMemberVO insert(OauthMemberVO oauthMemberVO);
    public OauthMemberVO update(OauthMemberVO oauthMemberVO);
    public OauthMemberVO selectById(Long id);
    public OauthMemberVO selectByMemberEmailAndMemberPassword(String memberEmail, String memberPassword);
    public OauthMemberVO updateById(OauthMemberVO oauthMemberVO);
    public Long deleteById(Long id);



}
