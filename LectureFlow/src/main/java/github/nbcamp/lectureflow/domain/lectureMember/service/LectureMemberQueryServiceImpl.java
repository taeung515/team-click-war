package github.nbcamp.lectureflow.domain.lectureMember.service;

import github.nbcamp.lectureflow.domain.lectureMember.dto.response.LectureEnrollCountResponse;
import github.nbcamp.lectureflow.domain.lectureMember.dto.response.LectureMemberListResponse;
import github.nbcamp.lectureflow.domain.lectureMember.repository.LectureMemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureMemberQueryServiceImpl implements LectureMemberQueryService{


    private final LectureMemberQueryRepository lectureMemberQueryRepository;

    @Override
    public List<LectureMemberListResponse> getLectureMember(Long memberId) {
        return lectureMemberQueryRepository.getLectureMember(memberId);
    }

    @Override
    public LectureEnrollCountResponse getLectureEnrollCount(Long lectureId) {
        return lectureMemberQueryRepository.getLectureEnrollCount(lectureId);
    }


}
