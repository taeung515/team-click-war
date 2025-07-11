package github.nbcamp.lectureflow.domain.lectureMember.controller;


import github.nbcamp.lectureflow.domain.lectureMember.dto.request.CreateLectureMemberRequest;
import github.nbcamp.lectureflow.domain.lectureMember.dto.response.CreateLectureMemberResponse;
import github.nbcamp.lectureflow.domain.lectureMember.service.LectureMemberService;
import github.nbcamp.lectureflow.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures/enroll")
public class LectureMemberController {

    private final LectureMemberService lectureMemberService;


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
