package github.nbcamp.lectureflow.domain.lectureMember.service;

import github.nbcamp.lectureflow.domain.lectureMember.dto.response.LectureEnrollCountResponse;
import github.nbcamp.lectureflow.domain.lectureMember.dto.response.LectureMemberListResponse;

import java.util.List;

public interface LectureMemberQueryService {

    List<LectureMemberListResponse> getLectureMember(Long memberId);

    LectureEnrollCountResponse getLectureEnrollCount(Long lectureId);
}
