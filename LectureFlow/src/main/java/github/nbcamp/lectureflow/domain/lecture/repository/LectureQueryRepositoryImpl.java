package github.nbcamp.lectureflow.domain.lecture.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import github.nbcamp.lectureflow.domain.lecture.dto.request.LectureSearchCondition;
import github.nbcamp.lectureflow.domain.lecture.dto.response.LectureDetailResponse;
import github.nbcamp.lectureflow.domain.lecture.dto.response.QLectureDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static github.nbcamp.lectureflow.global.entity.QLecture.lecture;
import static github.nbcamp.lectureflow.global.entity.QLectureMember.lectureMember;

@Repository
@RequiredArgsConstructor
public class LectureQueryRepositoryImpl implements LectureQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public Page<LectureDetailResponse> findByCondition(Pageable pageable, LectureSearchCondition condition) {

        BooleanBuilder cond = createCondition(condition);

        List<LectureDetailResponse> results = query
                .select(new QLectureDetailResponse(
                        lecture.id,
                        lecture.majorOrGeneral,
                        lecture.department,
                        lecture.gradeLevel,
                        lecture.isForeignLanguage,
                        lecture.lectureName,
                        lecture.grade,
                        lecture.professor,
                        lecture.day,
                        lecture.startTime,
                        lecture.endTime,
                        lecture.classroom,
                        lecture.maxStudent
                ))
                .from(lecture)
                .where(cond)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(lecture.lectureName.asc())
                .fetch();

        return new PageImpl<>(results, pageable, results.size());
    }

    private BooleanBuilder createCondition(LectureSearchCondition condition) {
        BooleanBuilder builder = new BooleanBuilder();

        // 1. 강의 정원 미달 조건
        builder.and(
                lecture.maxStudent.gt(
                        JPAExpressions
                                .select(lectureMember.count())
                                .from(lectureMember)
                                .where(lectureMember.lecture.eq(lecture))
                )
        );

        // 2. 키워드 (강의명 / 교수명)
        if (StringUtils.hasText(condition.getKeyword())) {
            builder.and(
                    lecture.lectureName.containsIgnoreCase(condition.getKeyword())
                            .or(lecture.professor.containsIgnoreCase(condition.getKeyword()))
            );
        }

        // 전공 / 교양
        if (condition.getMajorOrGeneral() != null) {
            builder.and(lecture.majorOrGeneral.eq(condition.getMajorOrGeneral()));
        }

        return builder;
    }
}
