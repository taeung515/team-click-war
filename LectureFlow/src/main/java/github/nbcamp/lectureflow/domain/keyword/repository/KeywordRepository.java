package github.nbcamp.lectureflow.domain.keyword.repository;

import github.nbcamp.lectureflow.global.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
}
