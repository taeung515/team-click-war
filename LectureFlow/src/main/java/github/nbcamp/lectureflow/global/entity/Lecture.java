package github.nbcamp.lectureflow.global.entity;

import github.nbcamp.lectureflow.domain.lecture.dto.request.LectureUploadRequestDto;
import github.nbcamp.lectureflow.global.enums.Day;
import github.nbcamp.lectureflow.global.enums.Department;
import github.nbcamp.lectureflow.global.enums.MajorOrGeneral;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "lectures")
public class Lecture extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //전공/교양
    @Column(name = "major_or_general", nullable = false)
    @Enumerated(EnumType.STRING)
    private MajorOrGeneral majorOrGeneral;

    //소속: 컴공, 또는 교양의 문화와 예술 등등..
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Department department;

    //학년
    @Column(name = "grade_level", nullable = false)
    private Integer gradeLevel;

    //원어 강의 여부
    @Column(name = "is_foreign_language", nullable = false)
    private Boolean isForeignLanguage;

    //강의명
    @Column(name = "lecture_name", nullable = false)
    private String lectureName;

    //학점
    @Column(nullable = false)
    private Integer grade;

    //담당교수
    @Column(nullable = false)
    private String professor;

    //요일
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Day day;

    //시작시간
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    //종료시간
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    //강의실: int로 바꿀까요
    @Column(nullable = false)
    private Integer classroom;

    //제한 인원
    @Column(name = "max_student", nullable = false)
    private Integer maxStudent;

    public static Lecture of(MajorOrGeneral majorOrGeneral, Department department, Integer gradeLevel, Boolean isForeignLanguage, String lectureName, Integer grade, String professor, Day day, LocalTime startTime, LocalTime endTime, Integer classroom, Integer maxStudent) {
        Lecture lecture = new Lecture();
        lecture.majorOrGeneral = majorOrGeneral;
        lecture.department = department;
        lecture.gradeLevel = gradeLevel;
        lecture.isForeignLanguage = isForeignLanguage;
        lecture.lectureName = lectureName;
        lecture.grade = grade;
        lecture.professor = professor;
        lecture.day = day;
        lecture.startTime = startTime;
        lecture.endTime = endTime;
        lecture.classroom = classroom;
        lecture.maxStudent = maxStudent;

        return lecture;
    }

    public static Lecture of(LectureUploadRequestDto lectureUploadRequestDto) {
        Lecture lecture = new Lecture();
        lecture.majorOrGeneral = lectureUploadRequestDto.getMajorOrGeneral();
        lecture.department = lectureUploadRequestDto.getDepartment();
        lecture.gradeLevel = lectureUploadRequestDto.getGradeLevel();
        lecture.isForeignLanguage = lectureUploadRequestDto.getIsForeignLanguage();
        lecture.lectureName = lectureUploadRequestDto.getLectureName();
        lecture.grade = lectureUploadRequestDto.getGrade();
        lecture.professor = lectureUploadRequestDto.getProfessor();
        lecture.day = lectureUploadRequestDto.getDay();
        lecture.startTime = lectureUploadRequestDto.getStartTime();
        lecture.endTime = lectureUploadRequestDto.getEndTime();
        lecture.classroom = lectureUploadRequestDto.getClassroom();
        lecture.maxStudent = lectureUploadRequestDto.getMaxStudent();

        return lecture;
    }
}
