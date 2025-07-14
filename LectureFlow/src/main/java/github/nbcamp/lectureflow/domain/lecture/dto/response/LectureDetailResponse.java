package github.nbcamp.lectureflow.domain.lecture.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import github.nbcamp.lectureflow.global.entity.Lecture;
import github.nbcamp.lectureflow.domain.lecture.enums.Day;
import github.nbcamp.lectureflow.domain.lecture.enums.Department;
import github.nbcamp.lectureflow.domain.lecture.enums.MajorOrGeneral;
import lombok.Getter;

import java.time.LocalTime;

@Getter
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
    private final int classroom;
    private final int maxStudent;

    @QueryProjection
    public LectureDetailResponse(Long lectureId, MajorOrGeneral majorOrGeneral, Department department, int gradeLevel, boolean isForeignLanguage, String lectureName, int grade, String professor, Day day, LocalTime startTime, LocalTime endTime, int classroom, int maxStudent) {
        this.lectureId = lectureId;
        this.majorOrGeneral = majorOrGeneral;
        this.department = department;
        this.gradeLevel = gradeLevel;
        this.isForeignLanguage = isForeignLanguage;
        this.lectureName = lectureName;
        this.grade = grade;
        this.professor = professor;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroom = classroom;
        this.maxStudent = maxStudent;
    }

    public static LectureDetailResponse of(Lecture lecture) {
        return new LectureDetailResponse(
                lecture.getId(),
                lecture.getMajorOrGeneral(),
                lecture.getDepartment(),
                lecture.getGradeLevel(),
                lecture.getIsForeignLanguage(),
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
