package github.nbcamp.lectureflow.domain.lecture.repository;

import github.nbcamp.lectureflow.global.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long>, LectureQueryRepository, LectureJdbcRepository {
}