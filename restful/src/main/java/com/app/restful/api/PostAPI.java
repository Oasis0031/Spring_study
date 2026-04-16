package com.app.restful.api;

import com.app.restful.domain.dto.ApiResponseDTO;
import com.app.restful.domain.dto.PostCreateRequestDTO;
import com.app.restful.domain.dto.PostDTO;
import com.app.restful.domain.dto.PostUpdateRequestDTO;
import com.app.restful.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostAPI {
    private final PostService postService;

    // 게시판 전체 조회
    @Operation(summary = "게시판 목록", description = "게시판 목록을 조회하는 서비스")
    @ApiResponse(responseCode = "200", description = "게시글 목록 조회 성공")
    @ApiResponse(responseCode = "404", description = "게시글 조회 실패")
    @Parameter(
            name = "order",
            description = "게시글의 정렬을 처리하는 파라미터",
            schema = @Schema(type = "string"),
            required = true,
            in = ParameterIn.QUERY
    )
    @GetMapping("")
    public ResponseEntity<ApiResponseDTO> getPostList(
            @RequestParam(value = "order", defaultValue = "desc") String order
    ){
        List<PostDTO> postList = postService.getPostList(order);

//        .status: 상태코드 -> mdn 상태코드
//        .body: 응답 데이터 -> ApiResponseDTO
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO
                        .of("게시글 목록 조회 성공", postList));
    }

    // 게시글 단일 조회
    @Operation(summary = "게시판 상세 보기", description = "해당 번호의 게시글 내용을 상세하게 보여주는 서비스")
    @ApiResponse(responseCode = "200", description = "게시글 조회 성공")
    @ApiResponse(responseCode = "404", description = "게시글 조회 실패")
    @Parameter(
            name = "id",
            description = "게시글의 번호",
            schema = @Schema(type = "number"),
            required = true,
            in = ParameterIn.PATH
    )
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> getPostDetail(@PathVariable Long id){
        PostDTO post = postService.getPostDetail(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("게시물 조회 완료", post));
    }

    // 게시글 작성
    @PostMapping("")
    @ApiResponse(responseCode = "201", description = "게시글 작성 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    @Operation(summary = "게시글 추가", description = "게시글 내용을 받으면 추가해주는 서비스")
    public ResponseEntity<ApiResponseDTO> createPost(@RequestBody PostCreateRequestDTO postCreateRequestDTO){
        // memberId 추출 (임의로 넣은 것!)
        postService.createPost(postCreateRequestDTO, 1L);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDTO.of("게시물 작성 성공"));
    }

    // 게시글 수정
    @Operation(summary = "게시판 수정", description = "해당 번호의 게시글 내용을 변경해주는 서비스")
    @ApiResponse(responseCode = "200", description = "게시글 수정 완료")
    @ApiResponse(responseCode = "404", description = "게시글 없음")
    @Parameter(
            name = "id",
            description = "게시글의 번호",
            schema = @Schema(type = "number"),
            required = true,
            in = ParameterIn.PATH
    )
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> modifyPost(
            @PathVariable Long id,
            @RequestBody PostUpdateRequestDTO postUpdateRequestDTO
    ){
        postService.modifyPost(postUpdateRequestDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("게시글 수정 완료"));
    }

    // 게시글 삭제
    @Operation(summary = "게시판 삭제", description = "해당 번호의 게시글 삭제해 주는 서비스")
    @ApiResponse(responseCode = "204", description = "게시글 삭제 완료")
    @ApiResponse(responseCode = "404", description = "게시글 없음")
    @Parameter(
            name = "id",
            description = "게시글의 번호",
            schema = @Schema(type = "number"),
            required = true,
            in = ParameterIn.PATH
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> removePost(@PathVariable Long id){
        postService.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponseDTO.of("게시물 삭제 완료"));
    }

}
