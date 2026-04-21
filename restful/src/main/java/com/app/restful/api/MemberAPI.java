package com.app.restful.api;

import com.app.restful.domain.dto.ApiResponseDTO;
import com.app.restful.domain.dto.MemberJoinRequestDTO;
import com.app.restful.domain.dto.MemberResponseDTO;
import com.app.restful.domain.dto.MemberUpdateRequestDTO;
import com.app.restful.domain.vo.MemberVO;
import com.app.restful.service.MemberService;
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
import java.util.Optional;

// 서비스 호출
// 리턴값을 JSON으로 처리
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberAPI {

    private final MemberService memberService;

    // 회원 목록 조회 서비스
    @Operation(summary = "회원 목록 조회 서비스", description = "회원 목록을 조회해서 리스트로 반환하는 서비스")
    @ApiResponse(responseCode = "200", description = "회원 목록 조회 성공")
    @ApiResponse(responseCode = "400", description = "회원 조회 실패")
    @GetMapping("")
    public ResponseEntity<ApiResponseDTO> getMemberList(){
        List<MemberResponseDTO> memberInfoList = memberService.getMemberInfoList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseDTO.of("회원 전체 조회 성공", memberInfoList));
    }

    // 회원 정보 조회 서비스
    @Operation(summary = "회원 단일 조회 서비스", description = "회원 조회해서 객체로 반환하는 서비스")
    @ApiResponse(responseCode = "200", description = "회원 목록 조회 성공")
    @ApiResponse(responseCode = "400", description = "회원 조회 실패")
    @Parameter(
            name = "id",
            description = "회원 번호",
            required = true,
            in = ParameterIn.PATH,
            example = "1",
            schema = @Schema(type = "number") // 스키마 타입
    )
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> getMemberInfo(@PathVariable Long id){
        MemberResponseDTO foundMember = memberService.getMemberInfo(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("회원 조회 성공", foundMember));
    }

    // 회원 추가 서비스
    @Operation(summary = "회원가입 서비스", description = "회원 정보를 받아서 회원가입을 시켜주는 서비스")
    @ApiResponse(responseCode = "201", description = "회원가입 성공")
    @ApiResponse(responseCode = "409", description = "이메일 중복")
    @PostMapping("/join")
    public ResponseEntity<ApiResponseDTO> join(@RequestBody MemberJoinRequestDTO memberJoinRequestDTO){
        memberService.join(memberJoinRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDTO.of("회원 가입 성공"));
    }

    // 로그인
    @Operation(summary = "로그인 서비스", description = "이메일과 비밀번호를 검증 후 로그인 서비스")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    @ApiResponse(responseCode = "401", description = "로그인 실패")
    @ApiResponse(responseCode = "401", description = "토큰 없음")
    @ApiResponse(responseCode = "403", description = "권한 없음")
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO> login(@RequestBody MemberVO memberVO){
        MemberResponseDTO foundMember = memberService.login(memberVO);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("로그인 성공", foundMember));
    }

    @Operation(summary = "회원 정보 수정 서비스", description = "회원 정보를 업데이트 시켜주는 서비스")
    @ApiResponse(responseCode = "200", description = "회원 정보 수정 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 접근")
    @ApiResponse(responseCode = "401", description = "토큰 없음")
    @ApiResponse(responseCode = "403", description = "권한 없음")
    @Parameter(
            name = "id",
            description = "회원 번호",
            required = true,
            in = ParameterIn.PATH,
            example = "1",
            schema = @Schema(type = "number") // 스키마 타입
    )
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> updateMember(
        @RequestBody MemberUpdateRequestDTO memberUpdateRequestDTO,
        @PathVariable Long id
    ){
        memberUpdateRequestDTO.setId(id);
        memberService.updateMember(memberUpdateRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("회원 정보 수정 완료"));
    }

    // 삭제 컨트롤러
    @Operation(summary = "회원 탈퇴 서비스", description = "회원 아이디로 회원 탈퇴해주는 서비스")
    @ApiResponse(responseCode = "200", description = "회원 탈퇴 완료")
    @ApiResponse(responseCode = "401", description = "토큰 없음")
    @ApiResponse(responseCode = "403", description = "권한 없음")
    @Parameter(
            name = "id",
            description = "회원 번호",
            required = true,
            in = ParameterIn.PATH,
            example = "1",
            schema = @Schema(type = "number") // 스키마 타입
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> withdraw(@PathVariable Long id){
        memberService.withdraw(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("회원 탈퇴 완료"));
    }


}
