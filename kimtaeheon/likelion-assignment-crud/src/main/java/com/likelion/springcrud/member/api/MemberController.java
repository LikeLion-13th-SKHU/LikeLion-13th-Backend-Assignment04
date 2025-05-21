package com.likelion.springcrud.member.api;

import com.likelion.springcrud.common.error.SuccessCode;
import com.likelion.springcrud.common.template.ApiResponse;
import com.likelion.springcrud.member.api.dto.request.MemberSaveRequestDto;
import com.likelion.springcrud.member.api.dto.request.MemberUpdateRequestDto;
import com.likelion.springcrud.member.api.dto.response.MemberInfoResponseDto;
import com.likelion.springcrud.member.api.dto.response.MemberListResponseDto;
import com.likelion.springcrud.member.application.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/save")
    public ApiResponse<String> memberSave(@RequestBody @Valid MemberSaveRequestDto requestDto) {
        memberService.memberSave(requestDto);
        return ApiResponse.success(SuccessCode.MEMBER_SAVE_SUCCESS);
    }

    @GetMapping("/all")
    public ApiResponse<MemberListResponseDto> memberFindAll(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        MemberListResponseDto memberListResponseDto = memberService.memberFindAll(pageable);
        return ApiResponse.success(SuccessCode.GET_SUCCESS, memberListResponseDto);
    }

    @GetMapping("/{id}")
    public ApiResponse<MemberInfoResponseDto> memberFindOne(@PathVariable("id") Long id) {
        MemberInfoResponseDto memberInfoResponseDto = memberService.memberFindOne(id);
        return ApiResponse.success(SuccessCode.GET_SUCCESS, memberInfoResponseDto);
    }

    // 사용자 정보 수정
    @PatchMapping("/{id}")
    public ApiResponse<String> memberUpdate(@PathVariable("id") Long id,
                                               @RequestBody @Valid MemberUpdateRequestDto memberUpdateRequestDto) {
        memberService.memberUpdate(id, memberUpdateRequestDto);
        return ApiResponse.success(SuccessCode.MEMBER_UPDATE_SUCCESS);
    }

    // 사용자 삭제
    @DeleteMapping("/{id}")
    public ApiResponse<String> memberDelete(@PathVariable("id") Long id) {
        memberService.memberDelete(id);
        return ApiResponse.success(SuccessCode.MEMBER_DELETE_SUCCESS);
    }
}
