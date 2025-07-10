package github.nbcamp.lectureflow.domain.lecture.service;

import github.nbcamp.lectureflow.domain.lecture.dto.response.LectureDetailResponse;

public interface LectureQueryService {

    LectureDetailResponse getLecture(Long lectureId);

}
