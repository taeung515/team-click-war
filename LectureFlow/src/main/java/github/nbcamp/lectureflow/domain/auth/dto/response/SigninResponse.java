package github.nbcamp.lectureflow.domain.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SigninResponse {

    private final Long memberId;
    private final String token;

    public static SigninResponse of(Long memberId, String token) {
        return new SigninResponse(memberId, token);
    }
}
