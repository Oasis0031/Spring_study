package com.app.restful.controller;

import com.app.restful.domain.dto.ApiResponseDTO;
import com.app.restful.exception.MemberException;
import com.app.restful.exception.PostException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(PostException.class)
    public ResponseEntity<ApiResponseDTO> handlePostException(PostException e) {
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseDTO.of((e.getMessage())));
    }

    //  분기처리
    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ApiResponseDTO> handleMemberException(MemberException e) {
        return ResponseEntity.status(e.getStatus()).body(ApiResponseDTO.of(e.getMessage()));
    }
}