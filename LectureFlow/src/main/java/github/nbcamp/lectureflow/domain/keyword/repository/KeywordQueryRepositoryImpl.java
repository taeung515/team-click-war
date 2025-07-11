package github.nbcamp.lectureflow.domain.keyword.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static github.nbcamp.lectureflow.global.entity.QKeyword.keyword;


@Repository
@RequiredArgsConstructor
public class KeywordQueryRepositoryImpl implements KeywordQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public List<String> findPopularKeywords() {
        return query
                .select(keyword.searchKeyword)
                .from(keyword)
                .groupBy(keyword.searchKeyword)
                .orderBy(keyword.searchKeyword.count().desc())
                .limit(10)
                .fetch();
    }

    @Override
    public long deleteOldKeywords() {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        return query
                .delete(keyword)
                .where(keyword.createdAt.lt(sevenDaysAgo))
                .execute();
    }
}

