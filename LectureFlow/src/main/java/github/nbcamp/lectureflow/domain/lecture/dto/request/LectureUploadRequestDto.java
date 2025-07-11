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
    @NotNull
    private MajorOrGeneral majorOrGeneral;
    @NotNull
    private Department department;
    @NotNull
    private Integer gradeLevel;
    @NotNull
    private Boolean isForeignLanguage;
    @NotNull
    private String lectureName;
    @NotNull
    private Integer grade;
    @NotNull
    private String professor;
    @NotNull
    private Day day;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
    @NotNull
    private Integer classroom;
    @NotNull
    private Integer maxStudent;
}
