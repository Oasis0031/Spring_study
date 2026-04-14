package com.app.restful.api;

import com.app.restful.domain.dto.MemberJoinRequestDTO;
import com.app.restful.domain.dto.MemberResponseDTO;
import com.app.restful.domain.dto.MemberUpdateRequestDTO;
import com.app.restful.domain.vo.MemberVO;
import com.app.restful.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


// 리턴값을 JSON으로 처리
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberAPI {

    private final MemberService memberService;
    private final MemberVO memberVO;

    // 회원 목록 조회 서비스
    @Operation(summary = "회원 목록 조회 서비스", description = "회원 목록을 조회해서 리스트로 반환하는 서비스")
    @ApiResponse(responseCode = "200", description = "회원 목록 조회 성공")
    @GetMapping("")
    public List<MemberResponseDTO> getMemberList(){
        return memberService.getMemberInfoList();
    }

    // 회원 정보 조회 서비스
    @Operation(summary = "회원 단일 조회 서비스", description = "회원 조회해서 객체로 반환하는 서비스")
    @ApiResponse(responseCode = "200", description = "회원 목록 조회 성공")
    @Parameter(
            name = "id",
            description = "회원 번호",
            required = true,
            in = ParameterIn.PATH,
            example = "1",
            schema = @Schema(type = "number") // 스키마 타입
    )
    @GetMapping("/{id}")
    public MemberResponseDTO getMemberInfo(@PathVariable Long id){
        Optional<MemberResponseDTO> foundMember = Optional.ofNullable(memberService.getMemberInfo(id));
        if(foundMember.isPresent()){
            return foundMember.get();
        }
        return new MemberResponseDTO();
    }

    // 회원 추가 서비스
    @Operation(summary = "회원가입 서비스", description = "회원 정보를 받아서 회원가입을 시켜주는 서비스")
    @ApiResponse(responseCode = "201", description = "회원가입 성공")
    @PostMapping("/join")
    public void join(@RequestBody MemberJoinRequestDTO memberJoinRequestDTO){
        memberService.join(memberJoinRequestDTO);
    }

    @Operation(summary = "로그인 서비스", description = "이메일과 비밀번호로 로그인 서비스")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    @PostMapping("/login")
    public MemberResponseDTO login(@RequestBody MemberVO memberVO){
       return memberService.login(memberVO);
    }

    @Operation(summary = "회원 정보 수정 서비스", description = "회원 정보를 업데이트 시켜주는 서비스")
    @ApiResponse(responseCode = "200", description = "회원 정보 수정 성공")
    @Parameter(
            name = "id",
            description = "회원 번호",
            required = true,
            in = ParameterIn.PATH,
            example = "1",
            schema = @Schema(type = "number") // 스키마 타입
    )
    @PutMapping("/{id}")
    public void updateMember(
            @RequestBody MemberUpdateRequestDTO memberUpdateRequestDTO,
            @PathVariable Long id
    ){
            memberUpdateRequestDTO.setId(id);
            memberService.updateMember(memberUpdateRequestDTO);
    }

    //삭제 컨트롤러
    @Operation(summary = "회원 탈퇴 서비스", description = "회원 정보를 업데이트 시켜주는 서비스")
    @ApiResponse(responseCode = "204", description = "회원 탈퇴 성공")
    @Parameter(
            name = "id",
            description = "회원 번호",
            required = true,
            in = ParameterIn.PATH,
            example = "1",
            schema = @Schema(type = "number") // 스키마 타입
    )
    @DeleteMapping("/{id")
    public void deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
    }

}
