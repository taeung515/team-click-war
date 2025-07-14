package github.nbcamp.lectureflow.domain.lectureMember.repository;

import github.nbcamp.lectureflow.domain.lectureMember.dto.response.LectureEnrollCountResponse;
import github.nbcamp.lectureflow.domain.lectureMember.dto.response.LectureMemberListResponse;
import github.nbcamp.lectureflow.global.entity.LectureMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureMemberQueryRepository extends JpaRepository<LectureMember,Long> {

    @Query("""
    SELECT lm.id, l.id, l.lectureName, l.majorOrGeneral, l.gradeLevel,
        l.department, l.classroom, l.day, l.professor, l.maxStudent, lm.isRetake
    FROM LectureMember lm
    JOIN lm.lecture l
    WHERE lm.member.id = :memberId
""")
    List<LectureMemberListResponse> getLectureMember(@Param("memberId") Long memberId);


    @Query("""
        SELECT l.id, l.maxStudent, COUNT (lm)
        FROM LectureMember lm
        JOIN lm.lecture l
        WHERE l.id = :lectureId
        GROUP BY l.id, l.maxStudent
""")
    LectureEnrollCountResponse getLectureEnrollCount(@Param("lectureId") Long lectureId);
}

