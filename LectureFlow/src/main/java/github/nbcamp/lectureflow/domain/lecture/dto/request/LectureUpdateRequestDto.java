package github.nbcamp.lectureflow.domain.lecture.dto.request;

import github.nbcamp.lectureflow.global.enums.Day;
import github.nbcamp.lectureflow.global.enums.Department;
import github.nbcamp.lectureflow.global.enums.MajorOrGeneral;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class LectureUpdateRequestDto {
    private MajorOrGeneral majorOrGeneral;
    private Department department;
    private Integer gradeLevel;
    private Boolean isForeignLanguage;
    private String lectureName;
    private Integer grade;
    private String professor;
    private Day day;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer classroom;
    private Integer maxStudent;
}