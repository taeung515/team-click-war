package github.nbcamp.lectureflow.domain.auth.dto.request;

import github.nbcamp.lectureflow.domain.member.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupRequest {

    @NotBlank(message = "이메일은 필수입니다.")
    private final String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private final String password;

    @NotBlank(message = "사용자 이름은 필수입니다.")
    private final String name;

    private final String phone;

    @NotNull(message = "역할은 필수입니다.")
    private final Role role;

}
