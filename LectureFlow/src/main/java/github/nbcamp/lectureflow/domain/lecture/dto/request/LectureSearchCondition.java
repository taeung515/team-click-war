package github.nbcamp.lectureflow.domain.lecture.dto.request;

import github.nbcamp.lectureflow.domain.lecture.enums.MajorOrGeneral;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LectureSearchCondition {

    // 강의명, 교수명
    private final String keyword;

    private final MajorOrGeneral majorOrGeneral;

}
