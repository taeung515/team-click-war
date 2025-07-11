package github.nbcamp.lectureflow.domain.lectureMember.repository;

import github.nbcamp.lectureflow.global.entity.LectureMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureMemberRepository extends JpaRepository<LectureMember, Long> {

    List<LectureMember> findAllByMemberIdAndLectureId(Long memberId, Long lectureId);

    Long countByLectureId(Long lectureMemberId);
}
