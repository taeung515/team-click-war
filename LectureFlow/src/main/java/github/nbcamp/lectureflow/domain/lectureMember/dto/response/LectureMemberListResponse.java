package github.nbcamp.lectureflow.domain.lectureMember.dto.response;

import github.nbcamp.lectureflow.global.enums.Day;
import github.nbcamp.lectureflow.global.enums.Department;
import github.nbcamp.lectureflow.global.enums.MajorOrGeneral;
import lombok.AllArgsConstructor;
import lombok.Getter;

    @Getter
    @AllArgsConstructor
    public class LectureMemberListResponse {

        private Long id;
        private Long lectureId;
        private String lectureName;
        private MajorOrGeneral majorOrGeneral;
        private int gradeLevel;
        private Department department;
        private int classroom;
        private Day day;
        private String professor;
        private int maxStudent;
        private boolean isRetake;


        public static LectureMemberListResponse of(
                Long id,
                Long lectureId,
                String lectureName,
                MajorOrGeneral majorOrGeneral,
                int gradeLevel,
                Department department,
                int classroom,
                Day day,
                String professor,
                int maxStudent,
                boolean isRetake
        ) {
            return new LectureMemberListResponse(
                    id,
                    lectureId,
                    lectureName,
                    majorOrGeneral,
                    gradeLevel,
                    department,
                    classroom,
                    day,
                    professor,
                    maxStudent,
                    isRetake
            );
        }
    }