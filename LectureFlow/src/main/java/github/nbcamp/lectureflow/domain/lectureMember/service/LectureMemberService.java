package github.nbcamp.lectureflow.domain.lectureMember.service;

import github.nbcamp.lectureflow.domain.lectureMember.dto.request.CreateLectureMemberRequest;
import github.nbcamp.lectureflow.domain.lectureMember.dto.response.CreateLectureMemberResponse;

public interface LectureMemberService {

    CreateLectureMemberResponse createLectureMember(CreateLectureMemberRequest lectureId, Long memberId);
}
