package github.nbcamp.lectureflow.domain.lecture.repository;

import github.nbcamp.lectureflow.global.entity.Lecture;
import java.util.List;

public interface LectureJdbcRepository {
    public void createLectures(List<Lecture> lectureList);
}
