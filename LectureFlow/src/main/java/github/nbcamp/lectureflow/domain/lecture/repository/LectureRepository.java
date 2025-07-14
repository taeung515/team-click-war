package github.nbcamp.lectureflow.domain.lecture.repository;

import github.nbcamp.lectureflow.global.entity.Lecture;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long>, LectureQueryRepository, LectureJdbcRepository {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT l FROM Lecture l WHERE l.id = :lectureId")
    Optional<Lecture> findByIdWithPessimisticLock(@Param("lectureId") Long lectureId);
}