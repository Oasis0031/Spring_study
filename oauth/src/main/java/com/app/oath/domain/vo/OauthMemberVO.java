package com.app.oath.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class OauthMemberVO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberPicture;
    private String memberName;
    private String memberNickname;
    private String memberProvider;
}
