package github.nbcamp.lectureflow.domain.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateRequestDto {

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password;

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    private String name;

    @NotBlank(message = "전화번호는 필수 입력값입니다.")
    private String phone;

    @Builder
    private MemberUpdateRequestDto(String email, String password, String nickname, String phone) {
        this.email = email;
        this.password = password;
        this.name = nickname;
        this.phone = phone;
    }
}
