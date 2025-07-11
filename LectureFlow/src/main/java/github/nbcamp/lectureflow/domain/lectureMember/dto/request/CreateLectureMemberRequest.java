package github.nbcamp.lectureflow.domain.lectureMember.dto.request;



import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateLectureMemberRequest {

    private final Long lectureId;
}
