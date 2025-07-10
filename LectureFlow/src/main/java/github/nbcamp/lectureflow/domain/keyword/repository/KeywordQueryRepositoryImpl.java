package github.nbcamp.lectureflow.domain.keyword.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static github.nbcamp.lectureflow.global.entity.QKeyword.keyword1;


@Repository
@RequiredArgsConstructor
public class KeywordQueryRepositoryImpl implements KeywordQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public List<String> findPopularKeywords() {
        return query
                .select(keyword1.keyword)
                .from(keyword1)
                .groupBy(keyword1.keyword)
                .orderBy(keyword1.keyword.count().desc())
                .limit(10)
                .fetch();
    }
}
