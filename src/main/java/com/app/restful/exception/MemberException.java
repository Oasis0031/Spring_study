package com.app.restful.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class MemberException extends RuntimeException{

<<<<<<< HEAD
//    에러 핸들링 분기 처리를 위한 목적
    private HttpStatus status;


    public MemberException(){;}
    public MemberException(String message, HttpStatus status){
=======
//  에러 핸들링 분기 처리를 위한 목적
    private HttpStatus status;

    public MemberException() {;}
    public MemberException(String message, HttpStatus status) {
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
        super(message);
        this.status = status;
    }
}
