package github.nbcamp.lectureflow.domain.lectureMember.dto.response;


import lombok.Getter;

@Getter
public class LectureEnrollCountResponse {
    private Long lectureId;
    private int maxStudent;
    private int student;

    // JPQL COUNT 결과(Long)를 int로 변환하는 생성자.
    public LectureEnrollCountResponse(Long lectureId, int maxStudent, Long student){
        this.lectureId=lectureId;
        this.maxStudent=maxStudent;
        this.student=student.intValue();
    }

    public static LectureEnrollCountResponse of(Long lectureId, int maxStudent, Long student) {
        return new LectureEnrollCountResponse(lectureId, maxStudent, student);
    }
}

