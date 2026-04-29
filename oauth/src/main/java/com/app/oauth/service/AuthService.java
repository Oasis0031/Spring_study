package com.app.oauth.service;

import com.app.oauth.domain.dto.response.JwtTokenDTO;
import com.app.oauth.domain.dto.response.MemberDTO;

public interface AuthService {

    // 로컬 로그인
    JwtTokenDTO login(MemberDTO memberDTO);

    // 소셜 로그인
    JwtTokenDTO socialLogin(MemberDTO memberDTO);

    // 로그아웃
    void logout(JwtTokenDTO jwtTokenDTO);

    // Redis에 Refresh Token 저장
    boolean saveRefreshToken(JwtTokenDTO jwtTokenDTO);

    // Redis에 저장된 Refresh Token이 유효한지 검증
    boolean validateRefreshToken(JwtTokenDTO jwtTokenDTO);

    // Redis에 블랙리스트 등록 (로그아웃 시 토큰 무효화)
    boolean saveBlackListedToken(JwtTokenDTO jwtTokenDTO);

    // Redis에 등록된 블랙리스트인지 검증
    boolean isBlackListedToken(JwtTokenDTO jwtTokenDTO);

    // Refresh 토큰을 검증하고, 새로운 AccessToken 발급
    JwtTokenDTO reissueAccessToken(JwtTokenDTO jwtTokenDTO);

    // 핸드폰 인증 코드 발송
    boolean sendMemberPhoneVerificationCode(String memberPhone);

    // 핸드폰 인증 코드 검증
    boolean verifyMemberPhoneVerificationCode(String memberPhone, String code);

    // 이메일 인증 코드 발송
    boolean sendMemberEmailVerificationCode(String to);

    // 이메일 인증 코드 검증
    boolean verifyMemberEmailVerificationCode(String memberEmail, String code);
}