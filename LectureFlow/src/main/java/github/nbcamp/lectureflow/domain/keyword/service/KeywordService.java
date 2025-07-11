package github.nbcamp.lectureflow.domain.keyword.service;

import github.nbcamp.lectureflow.domain.keyword.dto.TopTenResponse;

public interface KeywordService {
    void saveKeyword(String keyword);

    TopTenResponse getPopularKeywords();
}
