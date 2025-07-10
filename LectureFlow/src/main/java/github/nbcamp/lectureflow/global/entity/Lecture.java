package github.nbcamp.lectureflow.global.entity;

import github.nbcamp.lectureflow.domain.lecture.dto.LectureRequestDto;
import github.nbcamp.lectureflow.global.enums.Day;
import github.nbcamp.lectureflow.global.enums.Department;
import github.nbcamp.lectureflow.global.enums.MajorOrGeneral;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;

@Slf4j
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "lectures")
public class Lecture extends BaseEntity {

    // 기본키값 자동 생성 및 증가 시키도록 했으나 만약 따로 지정하고자 한다면 수정할 예정입니다.
    // @Id
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

    //학년
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

    @Column(name = "click_count")
    private Integer clickCount;

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
        lecture.clickCount = 0;

        return lecture;
    }

    public static Lecture of(LectureRequestDto.UploadDto uploadDto) {
        Lecture lecture = new Lecture();
        lecture.majorOrGeneral = uploadDto.getMajorOrGeneral();
        lecture.department = uploadDto.getDepartment();
        lecture.gradeLevel = uploadDto.getGradeLevel();
        lecture.isForeignLanguage = uploadDto.getIsForeignLanguage();
        lecture.lectureName = uploadDto.getLectureName();
        lecture.grade = uploadDto.getGrade();
        lecture.professor = uploadDto.getProfessor();
        lecture.day = uploadDto.getDay();
        lecture.startTime = uploadDto.getStartTime();
        lecture.endTime = uploadDto.getEndTime();
        lecture.classroom = uploadDto.getClassroom();
        lecture.maxStudent = uploadDto.getMaxStudent();
        lecture.clickCount = 0;

        return lecture;
    }

    //필요한 메서드는 그때그때 추가하겠습니다. 필드가 너무 많아서...

}
