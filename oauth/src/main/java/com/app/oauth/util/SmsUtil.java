package com.app.oauth.util;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SmsUtil {
    @Value("${coolsms.api.key}")
    private String apiKey;

    @Value("${coolsms.api.secret}")
    private String apiSecret;

    @Value("${spring.mail.username}")
    private String emailUsername;

    @Value("${spring.mail.password}")
    private String emailPassword;

    private DefaultMessageService messageService;
    private final JavaMailSenderImpl mailSender;

    @PostConstruct
    private void init(){
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
    }

    // AuthServiceImpl에서 smsUtil.sendOne()으로 호출하므로 이 메서드를 구현합니다.
    public SingleMessageSentResponse sendOne(String to, String content) {
        Message message = new Message();

        // "01012341234" <- 형태로 전송해야 함.
        String toPhoneNumber = to.replaceAll("-", "");

        message.setTo(toPhoneNumber);
        message.setFrom("01047099813"); // 발신자 번호 (Coolsms에 등록된 번호여야 함)
        message.setText(content);

        SingleMessageSentResponse response = this
                .messageService
                .sendOne(new SingleMessageSendingRequest(message));

        log.info("SMS 발송 결과: {}", response);
        return response;
    }

    // 기존 메서드는 유지하거나 sendOne을 내부적으로 호출하도록 변경할 수 있습니다.
    public SingleMessageSentResponse sendOneMemberPhone(String to, String content){
        return sendOne(to, content);
    }

    // 이메일 전송
    public void sendMemberEmail(String to, String subject, String content){
        MimeMessage mineMessage = mailSender.createMimeMessage();
        try {
        String context = content + "\"동전을 주다\"를 수학적으로 표현하면? 5%(offer cent)";
            MimeMessageHelper helper = new MimeMessageHelper(mineMessage, false, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject); // 제목
            helper.setText(context); // 내용
            helper.setFrom("oasiis@gmail.com", "Oasis"); // 보낸 이메일 , 보낸 사람 이름

            mailSender.send(mineMessage);
            log.info("이메일 발송 완료: {}", to);

        } catch (Exception e) {
            throw new RuntimeException("메일 전송 실패 " + e.getMessage());
        }
    }
}