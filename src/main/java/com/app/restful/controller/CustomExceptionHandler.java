package com.app.restful.controller;

import com.app.restful.domain.dto.ApiResponseDTO;
import com.app.restful.exception.MemberException;
import com.app.restful.exception.PostException;
<<<<<<< HEAD
import org.springframework.http.HttpStatus;
=======
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(PostException.class)
<<<<<<< HEAD
    public ResponseEntity<ApiResponseDTO> postException(PostException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseDTO.of(e.getMessage()));
=======
    public ResponseEntity<ApiResponseDTO> postException(PostException e){
        return ResponseEntity.status(e.getStatus()).body(ApiResponseDTO.of(e.getMessage()));
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
    }

//    분기처리
    @ExceptionHandler(MemberException.class)
<<<<<<< HEAD
    public ResponseEntity<ApiResponseDTO> memberException(MemberException e) {
        return ResponseEntity.status(e.getStatus()).body(ApiResponseDTO.of(e.getMessage()));
    }
}
=======
    public ResponseEntity<ApiResponseDTO> memberException(MemberException e){
        return ResponseEntity.status(e.getStatus()).body(ApiResponseDTO.of(e.getMessage()));
    }

}
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
