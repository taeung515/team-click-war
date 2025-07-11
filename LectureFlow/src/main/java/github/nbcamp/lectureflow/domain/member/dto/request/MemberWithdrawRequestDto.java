package github.nbcamp.lectureflow.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberWithdrawRequestDto {

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password;

    @Builder
    private MemberWithdrawRequestDto(String password) {
        this.password = password;
    }
}
