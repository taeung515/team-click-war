package github.nbcamp.lectureflow.domain.lectureMember.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateLectureMemberRequest {
    @NotBlank(message = "강의를 선택해주세요.")
    private final String lectureId;
}
