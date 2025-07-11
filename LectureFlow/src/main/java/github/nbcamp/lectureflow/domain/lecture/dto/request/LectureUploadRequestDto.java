package github.nbcamp.lectureflow.domain.lecture.dto.request;

import github.nbcamp.lectureflow.global.enums.Day;
import github.nbcamp.lectureflow.global.enums.Department;
import github.nbcamp.lectureflow.global.enums.MajorOrGeneral;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalTime;

@Builder
@Getter
@AllArgsConstructor
public class LectureUploadRequestDto {
    @NotNull(message = "전공 또는 교양 여부를 입력해주세요.")
    private MajorOrGeneral majorOrGeneral;
    @NotNull(message = "전공 또는 교양의 소속을 입력해주세요")
    private Department department;
    @NotNull(message = "학년을 입력해주세요.")
    private Integer gradeLevel;
    @NotNull(message = "원어 강의 여부를 입력해주세요.")
    private Boolean isForeignLanguage;
    @NotNull(message = "강의명을 입력해주세요.")
    private String lectureName;
    @NotNull(message = "학점을 입력해주세요.")
    private Integer grade;
    @NotNull(message = "교수명을 입력해주세요.")
    private String professor;
    @NotNull(message = "강의 요일을 입력해주세요.")
    private Day day;
    @NotNull(message = "강의 시작 시간을 입력해주세요.")
    private LocalTime startTime;
    @NotNull(message = "강의 종료 시간을 입력해주세요.")
    private LocalTime endTime;
    @NotNull(message = "강의실을 입력해주세요.")
    private Integer classroom;
    @NotNull(message = "수강 제한 인원을 입력해주세요.")
    private Integer maxStudent;
}
