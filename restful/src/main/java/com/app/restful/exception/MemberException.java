package com.app.restful.exception;

import java.lang.reflect.Member;

public class MemberException extends RuntimeException {
    public MemberException() {
        ;
    }

    public MemberException(String message) {
        super(message);
    }

}
