package com.app.oauth.service;

import com.app.oauth.domain.dto.response.JwtTokenDTO;
import com.app.oauth.domain.dto.response.MemberDTO;
import com.app.oauth.domain.vo.MemberVO;
import com.app.oauth.domain.vo.SocialMemberVO;
import com.app.oauth.exception.JwtTokenException;
import com.app.oauth.exception.MemberException;
import com.app.oauth.repository.MemberDAO;
import com.app.oauth.repository.SocialMemberDAO;
import com.app.oauth.util.AuthCodeGenerator;
import com.app.oauth.util.JwtTokenUtil;
import com.app.oauth.util.SmsUtil;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Transactional(rollbackFor = {Exception.class})
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final JavaMailSenderImpl mailSender;

    @Value("${jwt.token-blacklist-prefix}")
    private String BLACKLIST_TOKEN_PREFIX;

    @Value("${jwt.refresh-blacklist-prefix}")
    private String REFRESH_TOKEN_PREFIX;

    private final MemberDAO memberDAO;
    private final SocialMemberDAO socialMemberDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    // RedisConfig 설정에 맞춰 String, String으로 타입 일치
    private final RedisTemplate<String, String> redisTemplate;
    private final SmsUtil smsUtil;

    //    일반 로그인
//    순수데이터(JwtTokenDTO) 반환
    @Override
    public JwtTokenDTO login(MemberDTO memberDTO) {
        // 사용자가 맞는지 (이메일, 비밀번호, 프로바이더(local)

        // early return
        MemberVO memberVO = MemberVO.from(memberDTO);
        log.info("memberDTO: {}", memberDTO);
        // 회원 유무 검사
        MemberDTO foundMember = memberDAO
                .findMemberByMemberEmailAndSocialMemberProvider(memberDTO)
                .orElseThrow(() -> {
                    throw new MemberException("회원이 아닙니다.", HttpStatus.BAD_REQUEST);
                });

        // 회원 비밀번호 일치 검사
        // 화면에서 받은 비밀번호, DB에 있는 비밀번호 검사
        if(!passwordEncoder.matches(memberVO.getMemberPassword(), foundMember.getMemberPassword())){
            throw new MemberException("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 토큰 생성(access, refresh)
        Map<String, String> claims = new HashMap<>();
        claims.put("id", foundMember.getId().toString());
        claims.put("memberEmail", foundMember.getMemberEmail());
        claims.put("socialMemberProvider", "local");

        String accessToken = jwtTokenUtil.generateAccessToken(claims);
        String refreshToken = jwtTokenUtil.generateRefreshToken(claims);

        log.info("accessToken: {}", accessToken);
        log.info("refreshToken: {}", refreshToken);

        JwtTokenDTO jwtTokenDTO = new JwtTokenDTO();

        jwtTokenDTO.setAccessToken(accessToken);
        jwtTokenDTO.setRefreshToken(refreshToken);

        // redis에 refresh 토큰 저장
        saveRefreshToken(jwtTokenDTO);

        return jwtTokenDTO;
    }

    @Override
    public JwtTokenDTO socialLogin(MemberDTO memberDTO) {

        JwtTokenDTO jwtTokenDTO = new JwtTokenDTO();
        Map<String, String> claims = new HashMap<String, String>();


        if(memberDAO.existsMemberByMemberEmailAndSocialMemberProvider(memberDTO)){
            // 만약 유저가 있다면 -> 토큰 발급(id)
            // 조회
            MemberDTO foundMember = memberDAO
                    .findMemberByMemberEmailAndSocialMemberProvider(memberDTO)
                    .orElseThrow(() -> { throw new MemberException("socialLogin 회원 조회 실패", HttpStatus.BAD_REQUEST);});

            claims.put("id", foundMember.getId().toString());

        }else {
            // 만약 유저가 없다면 회원가입 후 -> 토큰 발급
            MemberVO memberVO = MemberVO.from(memberDTO);
            SocialMemberVO socialMemberVO = SocialMemberVO.from(memberDTO);

            memberDAO.save(memberVO);
            socialMemberVO.setMemberId(memberVO.getId());

            socialMemberDAO.save(socialMemberVO);
            claims.put("id", memberVO.getId().toString());
        }

        claims.put("memberEmail", memberDTO.getMemberEmail());
        claims.put("socialMemberProvider", memberDTO.getSocialMemberProvider());

        String accessToken = jwtTokenUtil.generateAccessToken(claims);
        String refreshToken = jwtTokenUtil.generateRefreshToken(claims);

        jwtTokenDTO.setAccessToken(accessToken);
        jwtTokenDTO.setRefreshToken(refreshToken);

        return jwtTokenDTO;
    }

    //실무에서는 Access Token을 받는 것이 관례
    @Override
    public void logout(JwtTokenDTO jwtTokenDTO) {
        try {
            if(jwtTokenDTO.getRefreshToken() != null) {
                saveBlackListedToken(jwtTokenDTO);
            }
        } catch (Exception e) {
            log.error("로그아웃 처리 중 오류 발생: {}", e.getMessage());
        }
    }

    // Redis에 refresh Token 저장
    // 콜론체이닝(:) 방법으로 저장
    @Override
    public boolean saveRefreshToken(JwtTokenDTO jwtTokenDTO) {
        String refreshToken = jwtTokenDTO.getRefreshToken();
        Long id = Long.parseLong((String)jwtTokenUtil.parseToken(refreshToken).get("id"));
        String key = REFRESH_TOKEN_PREFIX + id;

        try {
            redisTemplate.opsForValue().set(key, refreshToken, 30, TimeUnit.DAYS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Redis에 저장된 refresh Token이 유효한지 검증
    @Override
    public boolean validateRefreshToken(JwtTokenDTO jwtTokenDTO) {
        String refreshToken = jwtTokenDTO.getRefreshToken();
        Long id = Long.parseLong((String)jwtTokenUtil.parseToken(refreshToken).get("id"));
        String key = REFRESH_TOKEN_PREFIX + id;

        try {
            String storedToken = redisTemplate.opsForValue().get(key);
            if(storedToken == null || !refreshToken.equals(storedToken)){
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Redis에 블랙리스트를 등록 (로그아웃 시 토큰 무효화)
    @Override
    public boolean saveBlackListedToken(JwtTokenDTO jwtTokenDTO) {

        String refreshToken = null, refreshKey = null, accessToken = null, accessKey = null;
        Long refreshId = null, accessId = null;


        refreshToken = jwtTokenDTO.getRefreshToken();
        refreshId = Long.parseLong((String)jwtTokenUtil.parseToken(refreshToken).get("id"));
        refreshKey = BLACKLIST_TOKEN_PREFIX + refreshId;

        // accessToken 블랙리스트 처리를 위한 ID 추출 수정
        accessToken = jwtTokenDTO.getAccessToken();
        accessId = Long.parseLong((String)jwtTokenUtil.parseToken(accessToken).get("id"));
        accessKey = BLACKLIST_TOKEN_PREFIX + accessId;


        try {
            redisTemplate.opsForSet().add(refreshKey, refreshToken);
            redisTemplate.opsForSet().add(accessKey, accessToken);
            // TTL
            redisTemplate.expire(refreshKey, 30, TimeUnit.DAYS);
            redisTemplate.expire(accessKey, 1, TimeUnit.DAYS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Redis에 등록된 블랙리스트인지 검증
    @Override
    public boolean isBlackListedToken(JwtTokenDTO jwtTokenDTO) {
        String refreshToken = jwtTokenDTO.getRefreshToken();
        Long id = Long.parseLong((String)jwtTokenUtil.parseToken(refreshToken).get("id"));
        String key = BLACKLIST_TOKEN_PREFIX + id;

        try {
            Boolean isMember = redisTemplate.opsForSet().isMember(key, refreshToken);
            return isMember != null && isMember;
        } catch (Exception e) {
            return false;
        }
    }

    // refreshToken 토큰 -> accessToken을 재발급
    @Override
    public JwtTokenDTO reissueAccessToken(JwtTokenDTO jwtTokenDTO) {
        String refreshToken = jwtTokenDTO.getRefreshToken();
        Long id = Long.parseLong((String)jwtTokenUtil.parseToken(refreshToken).get("id"));

        if(isBlackListedToken(jwtTokenDTO)){
            throw new JwtTokenException("이미 로그아웃 된 토큰입니다", HttpStatus.BAD_REQUEST);
        }

        // 리프레쉬 검증
        if(!validateRefreshToken(jwtTokenDTO)){
            throw new JwtTokenException("유효하지 않은 토큰입니다", HttpStatus.BAD_REQUEST);
        }

        Map<String, String> claims = new HashMap<>();
        MemberDTO member = memberDAO
                .findMemberById(id).orElseThrow(() -> new MemberException("회원이 없습니다", HttpStatus.BAD_REQUEST));

        claims.put("id", member.getId().toString());
        claims.put("memberEmail", member.getMemberEmail());
        claims.put("socialMemberProvider", member.getSocialMemberProvider());

        // 새로운 토큰 생성
        String newAccessToken = jwtTokenUtil.generateAccessToken(claims);
        jwtTokenDTO.setAccessToken(newAccessToken);

        return jwtTokenDTO;
    }

    // 핸드폰 인증 코드 발송
    @Override
    public boolean sendMemberPhoneVerificationCode(String memberPhone) {
        String formattedPhone = memberPhone.replace("-","");

        String message = null;
        String code = AuthCodeGenerator.generateAuthCode();
        message = "[웹 발신]\n인증코드를 입력해주세요.\n{"+code+"}";
        smsUtil.sendOne(formattedPhone, message);

        // redis 저장
        String key = "phone:" + formattedPhone;

        try {
            // 검증 로직에 맞춰 Value로 저장
            redisTemplate.opsForValue().set(key, code, 30, TimeUnit.MINUTES);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 핸드폰 인증 코드 검증
    @Override
    public boolean verifyMemberPhoneVerificationCode(String memberPhone, String code) {
        String formattedPhone = memberPhone.replace("-","");
        String key = "phone:" + formattedPhone;

        try {
            String storedCode = redisTemplate.opsForValue().get(key);
            return code.equals(storedCode);
        } catch (Exception e){
            return false;
        }

    }

    // 이메일 인증 코드 발송
    @Override
    public boolean sendMemberEmailVerificationCode(String to) {
        String code = AuthCodeGenerator.generateAuthCode();
        String key = "email:" + to;

        try {
            // SmsUtil의 이메일 전송 메서드 활용
            smsUtil.sendMemberEmail(to, "인증번호 안내", "인증번호: [" + code + "]");
            redisTemplate.opsForValue().set(key, code, 30, TimeUnit.MINUTES);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 이메일 인증 코드 검증
    @Override
    public boolean verifyMemberEmailVerificationCode(String memberEmail, String code) {
        String key = "email:" + memberEmail;
        try {
            String storedCode = redisTemplate.opsForValue().get(key);
            return code.equals(storedCode);
        } catch (Exception e) {
            return false;
        }
    }

    // 이메일 전송 (내부 메서드로 사용 시)
    public void sendMemberEmail(String to, String subject, String context){
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false , "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(context);
            helper.setFrom("oasis0031@gmail.com", "Oasis");

            mailSender.send(mimeMessage);

        } catch (Exception e) {
            throw new RuntimeException("메일 전송 실패 " + e.getMessage());
        }
    }
}