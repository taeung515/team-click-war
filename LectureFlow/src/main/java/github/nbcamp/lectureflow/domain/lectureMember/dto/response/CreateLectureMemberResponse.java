package github.nbcamp.lectureflow.domain.lectureMember.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateLectureMemberResponse {
    private final Long lectureMemberId;
    private final Long memberId;
    private final Long lectureId;

    public static CreateLectureMemberResponse of(Long lectureMemberId, Long memberId, Long lectureId) {
        return new CreateLectureMemberResponse(lectureMemberId, memberId, lectureId);
    }

}
