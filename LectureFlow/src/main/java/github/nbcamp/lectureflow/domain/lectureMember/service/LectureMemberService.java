package github.nbcamp.lectureflow.domain.lectureMember.service;

import github.nbcamp.lectureflow.domain.lectureMember.dto.request.CreateLectureMemberRequest;
import github.nbcamp.lectureflow.domain.lectureMember.dto.response.CreateLectureMemberResponse;
import github.nbcamp.lectureflow.global.entity.Lecture;

public interface LectureMemberService {

    CreateLectureMemberResponse createLectureMember(CreateLectureMemberRequest lectureId, Long memberId);

    void deleteLectureMember(Long lectureMemberId, Long memberId);

    void deleteLectureMemberByLecture(Lecture lecture);

}
