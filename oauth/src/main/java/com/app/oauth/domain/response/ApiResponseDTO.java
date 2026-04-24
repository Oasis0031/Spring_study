package com.app.oauth.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDTO<T> {
    private boolean success;
    private String message;
    private T data;

    // 1. 메시지만 받는 생성자 (실패 시 주로 사용)
    public ApiResponseDTO(String message) {
        this.success = false; // 예외 상황에서 호출될 가능성이 높으므로 기본 false
        this.message = message;
    }

    // 2. 메시지와 데이터를 받는 생성자 (성공 시 주로 사용)
    public ApiResponseDTO(String message, T data) {
        this.success = true;
        this.message = message;
        this.data = data;
    }

    // 3. ExceptionHandler에서 사용하는 정적 팩토리 메서드
    public static <T> ApiResponseDTO<T> of(String message) {
        return new ApiResponseDTO<>(message);
    }

    // 4. 성공 시 데이터를 함께 보내는 정적 팩토리 메서드
    public static <T> ApiResponseDTO<T> of(String message, T data) {
        return new ApiResponseDTO<>(message, data);
    }
}