package com.app.oauth.service;

import com.app.oauth.handler.Oauth2LogInSuccessHandler;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class AuthServiceTests {

    @Autowired
    private AuthService authService;

    @Autowired
    private Oauth2LogInSuccessHandler oauth2LogInSuccessHandler;

    @Test
    @DisplayName("휴대폰 인증 코드 발송 테스트")
    public void sendMemberPhoneCodeTest(){
        // 실제 테스트 시 본인 번호 입력
        String memberPhone = "01024327082";
        boolean isSent = authService.sendMemberPhoneVerificationCode(memberPhone);

        log.info("인증 코드 발송 결과: {}", isSent);
        assertThat(isSent).isTrue();
    }

    @Test
    @DisplayName("OAuth2 로그인 성공 핸들러 테스트")
    public void oauth2HandlerTest() throws ServletException, IOException {
        // 1. 가짜(Mock) 요청 및 응답 객체 생성
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // 2. 가짜 OAuth2User 생성 (구글 로그인 가정)
        Map<String, Object> attributes = Map.of(
                "sub", "google-12345",
                "email", "test@gmail.com",
                "name", "홍길동"
        );

        OAuth2User oauth2User = new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                "sub"
        );

        // 3. 가짜 AuthenticationToken 생성
        OAuth2AuthenticationToken authentication = new OAuth2AuthenticationToken(
                oauth2User,
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                "google" // registrationId
        );

        // 4. 핸들러 실행
        oauth2LogInSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        // 5. 검증
        // 쿠키가 생성되었는지 확인
        assertThat(response.getCookie("accessToken")).isNotNull();
        assertThat(response.getCookie("refreshToken")).isNotNull();

        log.info("Access Cookie: {}", response.getCookie("accessToken").getValue());
        log.info("Redirect URL: {}", response.getRedirectedUrl());

        // 리다이렉트 경로 확인
        assertThat(response.getRedirectedUrl()).isEqualTo("http://localhost:3000");
    }
}