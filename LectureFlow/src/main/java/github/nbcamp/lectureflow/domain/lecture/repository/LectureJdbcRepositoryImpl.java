package github.nbcamp.lectureflow.domain.lecture.repository;

import github.nbcamp.lectureflow.global.entity.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureJdbcRepositoryImpl implements LectureJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void createLectures(List<Lecture> lectureList) {
        String sql = "INSERT INTO lectures (classroom,created_at,day,department,end_time,grade,grade_level,is_foreign_language,lecture_name,major_or_general,max_student,professor,start_time,updated_at) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.batchUpdate(sql,
                lectureList,
                lectureList.size(),
                (ps, lecture) -> {
                    LocalDateTime localDateTime = LocalDateTime.now();
                    ps.setInt(1, lecture.getClassroom());
                    ps.setObject(2, localDateTime);//
                    ps.setString(3, String.valueOf(lecture.getDay()));
                    ps.setString(4, String.valueOf(lecture.getDepartment()));
                    ps.setObject(5, lecture.getEndTime());
                    ps.setObject(6, lecture.getGrade());
                    ps.setObject(7, lecture.getGradeLevel());
                    ps.setObject(8, lecture.getIsForeignLanguage());
                    ps.setObject(9, lecture.getLectureName());
                    ps.setString(10, String.valueOf(lecture.getMajorOrGeneral()));
                    ps.setObject(11, lecture.getMaxStudent());
                    ps.setObject(12, lecture.getProfessor());
                    ps.setObject(13, lecture.getStartTime());
                    ps.setObject(14, localDateTime);//
                }
        );
    }
}