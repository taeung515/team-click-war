package github.nbcamp.lectureflow.domain.lecture.service;

import github.nbcamp.lectureflow.domain.keyword.service.KeywordService;
import github.nbcamp.lectureflow.domain.lecture.dto.request.LectureSearchCondition;
import github.nbcamp.lectureflow.domain.lecture.dto.response.LectureDetailResponse;
import github.nbcamp.lectureflow.domain.lecture.exception.LectureException;
import github.nbcamp.lectureflow.domain.lecture.repository.LectureRepository;
import github.nbcamp.lectureflow.global.entity.Lecture;
import github.nbcamp.lectureflow.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LectureQueryServiceImpl implements LectureQueryService {

    private final LectureRepository lectureRepository;
    private final KeywordService keywordService;

    @Override
    public LectureDetailResponse getLecture(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() -> new LectureException(ErrorCode.LECTURE_NOT_FOUND));
        return LectureDetailResponse.of(lecture);
    }

    @Override
    public Page<LectureDetailResponse> getLectures(Pageable pageable, LectureSearchCondition condition) {
        keywordService.saveKeyword(condition.getKeyword());
        return lectureRepository.findByCondition(pageable, condition);
    }
}
