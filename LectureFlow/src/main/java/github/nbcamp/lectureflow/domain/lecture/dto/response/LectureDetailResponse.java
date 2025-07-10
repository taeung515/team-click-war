package github.nbcamp.lectureflow.domain.lecture.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import github.nbcamp.lectureflow.global.entity.Lecture;
import github.nbcamp.lectureflow.global.enums.Day;
import github.nbcamp.lectureflow.global.enums.Department;
import github.nbcamp.lectureflow.global.enums.MajorOrGeneral;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class LectureDetailResponse {

    private final Long lectureId;
    private final MajorOrGeneral majorOrGeneral;
    private final Department department;
    private final int gradeLevel;
    private final boolean isForeignLanguage;
    private final String lectureName;
    private final int grade;
    private final String professor;
    private final Day day;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private final LocalTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private final LocalTime endTime;
    private final String classroom;
    private final int maxStudent;

    public static LectureDetailResponse of(Lecture lecture) {
        return new LectureDetailResponse(
                lecture.getId(),
                lecture.getMajorOrGeneral(),
                lecture.getDepartment(),
                lecture.getGradeLevel(),
                lecture.isForeignLanguage(),
                lecture.getLectureName(),
                lecture.getGrade(),
                lecture.getProfessor(),
                lecture.getDay(),
                lecture.getStartTime(),
                lecture.getEndTime(),
                lecture.getClassroom(),
                lecture.getMaxStudent()
        );
    }
}
