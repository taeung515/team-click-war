package github.nbcamp.lectureflow.domain.keyword.repository;

import java.util.List;

public interface KeywordQueryRepository {

    List<String> findPopularKeywords();
}
