package com.app.controller.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
//데이터의 직렬화, 고유번호를 매김. 이 VO는 외부 서버와 소통이 이루어지게 한다.
public class MemberVO implements Serializable {
        private Long id;
        private String memberEmail;
        private String memberPassword;
        private String memberName;
}
