package github.nbcamp.lectureflow.domain.keyword.controller;

import github.nbcamp.lectureflow.domain.keyword.dto.TopTenResponse;
import github.nbcamp.lectureflow.domain.keyword.service.KeywordService;
import github.nbcamp.lectureflow.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/keywords")
public class KeywordController {

    private final KeywordService keywordService;

    @GetMapping("/v1/popular")
    public ResponseEntity<ApiResponse<TopTenResponse>> getPopularKeywords() {
        TopTenResponse response = keywordService.getPopularKeywords();
        return ResponseEntity.ok(ApiResponse.success("탑 10 인기 검색어 조회 성공", response));
    }

    @GetMapping("/v2/popular")
    public ResponseEntity<ApiResponse<TopTenResponse>> getPopularKeywordsV2() {
        TopTenResponse response = keywordService.getPopularKeywordsV2();
        return ResponseEntity.ok(ApiResponse.success("탑 10 인기 검색어 조회 성공", response));
    }
}
