package github.nbcamp.lectureflow.domain.lectureMember.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateLectureMemberResponse {
    private final Long id;
    private final Long memberId;
    private final Long lectureId;

    public static CreateLectureMemberResponse of(Long id, Long memberId, Long lectureId) {
        return new CreateLectureMemberResponse(id, memberId, lectureId);
    }

}
