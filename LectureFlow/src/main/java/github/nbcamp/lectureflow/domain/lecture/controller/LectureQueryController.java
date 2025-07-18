package github.nbcamp.lectureflow.domain.lecture.controller;

import github.nbcamp.lectureflow.domain.lecture.dto.request.LectureSearchCondition;
import github.nbcamp.lectureflow.domain.lecture.dto.response.LectureDetailResponse;
import github.nbcamp.lectureflow.domain.lecture.service.LectureQueryService;
import github.nbcamp.lectureflow.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureQueryController {

    private final LectureQueryService lectureQueryService;

    @GetMapping("{lectureId}")
    public ResponseEntity<ApiResponse<LectureDetailResponse>> getLecture(@PathVariable Long lectureId) {
        LectureDetailResponse response = lectureQueryService.getLecture(lectureId);
        return ResponseEntity.ok(ApiResponse.success("Lecture 조회 성공", response));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<LectureDetailResponse>>> getLectures(Pageable pageable, LectureSearchCondition condition) {
        Page<LectureDetailResponse> responses = lectureQueryService.getLectures(pageable, condition);
        return ResponseEntity.ok(ApiResponse.success("Lectures 조회 성공", responses));
    }
}
