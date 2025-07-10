package github.nbcamp.lectureflow.domain.lecture.controller;

import github.nbcamp.lectureflow.domain.lecture.service.LectureService;
import github.nbcamp.lectureflow.domain.response.LectureResponse;
import github.nbcamp.lectureflow.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Secured("ROLE_ADMIN")
@RequestMapping("/admin")
public class LectureController {
    private final LectureService lectureService;

    @PostMapping("/lectures")
    public ResponseEntity<ApiResponse<Void>> createLectures(@RequestParam(value = "multipartFile") MultipartFile multipartFile) throws IOException {
        lectureService.createLecture(multipartFile);

        return ResponseEntity.ok(ApiResponse.success(LectureResponse.Lecture_UPLOAD_SUCCESS.getMessage(), null));
    }


}
