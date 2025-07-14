package github.nbcamp.lectureflow.domain.lecture.controller;

import github.nbcamp.lectureflow.domain.lecture.dto.request.LectureUpdateRequestDto;
import github.nbcamp.lectureflow.domain.lecture.dto.request.LectureUploadRequestDto;
import github.nbcamp.lectureflow.domain.lecture.service.LectureService;
import github.nbcamp.lectureflow.domain.response.LectureResponse;
import github.nbcamp.lectureflow.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Secured("ROLE_ADMIN")
@RequestMapping("/admin")
public class LectureAdminController {
    private final LectureService lectureService;

    @PostMapping("/lectures/upload")
    public ResponseEntity<ApiResponse<Void>> createLectures(@RequestParam(value = "multipartFile") MultipartFile multipartFile) throws IOException {
        lectureService.createLectures(multipartFile);

        return ResponseEntity.ok(ApiResponse.success(LectureResponse.LECTURE_UPLOAD_SUCCESS.getMessage(), null));
    }

    @PostMapping("/lectures")
    public ResponseEntity<ApiResponse<Void>> createLecture(@Valid @RequestBody LectureUploadRequestDto lectureUploadRequestDto) {
        lectureService.createLecture(lectureUploadRequestDto);

        return ResponseEntity.ok(ApiResponse.success(LectureResponse.LECTURE_UPLOAD_SUCCESS.getMessage(), null));
    }

    @PatchMapping("/lectures/{lectureId}")
    public ResponseEntity<ApiResponse<Void>> updateLectures(@RequestBody LectureUpdateRequestDto lectureUpdateRequestDto, @PathVariable Long lectureId) {
        lectureService.updateLecture(lectureUpdateRequestDto, lectureId);

        return ResponseEntity.ok(ApiResponse.success(LectureResponse.LECTURE_UPDATE_SUCCESS.getMessage(), null));
    }

    @DeleteMapping("/lectures/{lectureId}")
    public ResponseEntity<ApiResponse<Void>> deleteLectures(@PathVariable Long lectureId) {
        lectureService.deleteLecture(lectureId);

        return ResponseEntity.ok(ApiResponse.success(LectureResponse.LECTURE_DELETE_SUCCESS.getMessage(), null));
    }
}
