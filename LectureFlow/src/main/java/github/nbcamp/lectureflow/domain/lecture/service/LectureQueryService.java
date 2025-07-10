package github.nbcamp.lectureflow.domain.lecture.service;

import github.nbcamp.lectureflow.domain.lecture.dto.request.LectureSearchCondition;
import github.nbcamp.lectureflow.domain.lecture.dto.response.LectureDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LectureQueryService {

    LectureDetailResponse getLecture(Long lectureId);

    Page<LectureDetailResponse> getLectures(Pageable pageable, LectureSearchCondition condition);
}
