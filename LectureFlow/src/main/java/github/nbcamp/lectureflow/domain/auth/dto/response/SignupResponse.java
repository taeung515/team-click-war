package github.nbcamp.lectureflow.domain.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupResponse {

    private final Long memberId;

    public static SignupResponse of(Long memberId) {
        return new SignupResponse(memberId);
    }
}
