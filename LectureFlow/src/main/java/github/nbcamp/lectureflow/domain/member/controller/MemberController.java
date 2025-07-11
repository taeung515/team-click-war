package github.nbcamp.lectureflow.domain.member.controller;

import github.nbcamp.lectureflow.domain.member.dto.request.MemberUpdateRequestDto;
import github.nbcamp.lectureflow.domain.member.dto.request.MemberWithdrawRequestDto;
import github.nbcamp.lectureflow.domain.member.dto.response.MemberResponseDto;
import github.nbcamp.lectureflow.domain.member.service.MemberService;
import github.nbcamp.lectureflow.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    //내 정보 조회
    @GetMapping("/me")
    public ApiResponse<MemberResponseDto> getMyInfo(@AuthenticationPrincipal Long memberId) {
        MemberResponseDto response = memberService.getMyInfo(memberId);
        return ApiResponse.success("내 정보 조회 성공", response);
    }

    //회원 정보 수정
    @PutMapping("/me")
    public ApiResponse<Void> updateMember(
            @AuthenticationPrincipal Long memberId,
            @Valid @RequestBody MemberUpdateRequestDto requestDto) {

        memberService.updateMember(memberId, requestDto);
        return ApiResponse.success("회원 정보 수정 완료", null);
    }

    //회원 탈퇴
    @DeleteMapping("/withdraw")
    public ApiResponse<Void> withdrawMember(
            @AuthenticationPrincipal Long memberId,
            @Valid @RequestBody MemberWithdrawRequestDto requestDto) {

        memberService.withdrawMember(memberId, requestDto);
        return ApiResponse.success("회원 탈퇴 완료", null);
    }
}

