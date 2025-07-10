package github.nbcamp.lectureflow.domain.lecture.dto;

import github.nbcamp.lectureflow.global.enums.Day;
import github.nbcamp.lectureflow.global.enums.Department;
import github.nbcamp.lectureflow.global.enums.MajorOrGeneral;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class LectureRequestDto {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class UploadDto{
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

    @Getter
    @AllArgsConstructor
    public static class UpdateDto{
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
}
