package com.app.restful.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
<<<<<<< HEAD
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "서버 응답 DTO")
public class ApiResponseDTO<T> {

=======
@NoArgsConstructor @AllArgsConstructor
@Schema(description = "서버 응답 DTO")
public class ApiResponseDTO<T> {
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
    @Schema(description = "응답 메세지", example = "조회 성공", required = true)
    private String message;
    @Schema(description = "응답 데이터")
    private T data;

    public static<T> ApiResponseDTO of(String message){
        return new ApiResponseDTO<>(message, null);
    }

    public static<T> ApiResponseDTO<T> of(String message, T data){
        return new ApiResponseDTO<>(message, data);
    }


<<<<<<< HEAD
}
=======
}
>>>>>>> b308ed9b6a7618978f876e6e723bb1c4f40d5dd6
