package github.nbcamp.lectureflow.domain.member.dto.response;

import github.nbcamp.lectureflow.global.entity.Member;
import github.nbcamp.lectureflow.domain.member.enums.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {

    private Long id;
    private String email;
    private String name;
    private String phone;
    private Role role;

    @Builder
    private MemberResponseDto(Long id, String email, String name, String phone, Role role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.role = role;
    }

    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .phone(member.getPhone())
                .role(member.getRole())
                .build();
    }
}