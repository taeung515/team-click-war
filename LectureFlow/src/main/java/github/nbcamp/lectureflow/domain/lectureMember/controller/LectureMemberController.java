package github.nbcamp.lectureflow.domain.lectureMember.controller;


import github.nbcamp.lectureflow.domain.lectureMember.dto.request.CreateLectureMemberRequest;
import github.nbcamp.lectureflow.domain.lectureMember.dto.response.CreateLectureMemberResponse;
import github.nbcamp.lectureflow.domain.lectureMember.dto.response.LectureMemberListResponse;
import github.nbcamp.lectureflow.domain.lectureMember.service.LectureMemberQueryService;
import github.nbcamp.lectureflow.domain.lectureMember.service.LectureMemberService;
import github.nbcamp.lectureflow.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures/enroll")
public class LectureMemberController {

    private final LectureMemberService lectureMemberService;
    private final LectureMemberQueryService lectureMemberQueryService;

    //로그인한 사용자의 수강신청 목록 조회
    @GetMapping()
    public ResponseEntity<ApiResponse<List<LectureMemberListResponse>>> getLectureMember(@AuthenticationPrincipal Long memberId) {
        List<LectureMemberListResponse> response = lectureMemberQueryService.getLectureMember(memberId);
        return ResponseEntity.ok(ApiResponse.success("수강신청 목록 조회", response));
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<CreateLectureMemberResponse>> createLectureMember(@Valid @RequestBody CreateLectureMemberRequest request, @AuthenticationPrincipal Long memberId) {
        CreateLectureMemberResponse response = lectureMemberService.createLectureMember(request, memberId);
        return ResponseEntity.ok(ApiResponse.success("수강신청 완료", response));
    }

    @DeleteMapping("/{lectureMemberId}")
    public ResponseEntity<ApiResponse<Void>> deleteLectureMember(@PathVariable Long lectureMemberId, @AuthenticationPrincipal Long memberId) {
        lectureMemberService.deleteLectureMember(lectureMemberId,memberId);
        return ResponseEntity.ok(ApiResponse.success("수강취소 완료",null));
    }
}
