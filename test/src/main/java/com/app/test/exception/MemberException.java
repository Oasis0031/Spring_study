package com.app.test.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class MemberException extends RuntimeException{

    //객체를 받을 때 상태 코드도 같이 받는다.
    //에러 핸들링 분기 처리를 위한 목적
    private HttpStatus status;

    public MemberException() {;}
    public MemberException(String message, HttpStatus status) {

        super(message);
        this.status = status;
    }
}
