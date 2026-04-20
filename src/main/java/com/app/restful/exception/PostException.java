package com.app.restful.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class PostException extends RuntimeException{

    private HttpStatus status;

<<<<<<< HEAD
    public PostException(String message, HttpStatus status){
=======
    public PostException() {;}
    public PostException(String message, HttpStatus status) {
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
        super(message);
        this.status = status;
    }
}
