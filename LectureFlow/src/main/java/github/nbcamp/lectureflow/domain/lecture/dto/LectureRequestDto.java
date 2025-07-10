package github.nbcamp.lectureflow.domain.lecture.dto;

import github.nbcamp.lectureflow.global.enums.Day;
import github.nbcamp.lectureflow.global.enums.Department;
import github.nbcamp.lectureflow.global.enums.MajorOrGeneral;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@Getter
public class LectureRequestDto {
    private MajorOrGeneral majorOrGeneral;
    private Department department;
    private int gradeLevel;
    private boolean isForeignLanguage;
    private String lectureName;
    private int grade;
    private String professor;
    private Day day;
    private LocalTime startTime;
    private LocalTime endTime;
    private int classroom;
    private int maxStudent;

}
