package github.nbcamp.lectureflow.domain.lectureMember.repository;

import github.nbcamp.lectureflow.global.entity.LectureMember;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureMemberRepository extends JpaRepository<LectureMember, Long> {

    List<LectureMember> findAllByMemberIdAndLectureId(Long memberId, Long lectureId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT COUNT(lm) FROM LectureMember lm WHERE lm.lecture.id = :lectureId")
    Long countByLectureIdWithLock(@Param("lectureId") Long lectureId);




}
