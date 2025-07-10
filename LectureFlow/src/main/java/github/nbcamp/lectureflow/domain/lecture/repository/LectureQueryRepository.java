package github.nbcamp.lectureflow.domain.lecture.repository;

import github.nbcamp.lectureflow.domain.lecture.dto.request.LectureSearchCondition;
import github.nbcamp.lectureflow.domain.lecture.dto.response.LectureDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LectureQueryRepository {

    Page<LectureDetailResponse> findByCondition(Pageable pageable, LectureSearchCondition condition);

}
