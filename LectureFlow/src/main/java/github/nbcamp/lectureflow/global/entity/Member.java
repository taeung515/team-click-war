package github.nbcamp.lectureflow.global.entity;

import github.nbcamp.lectureflow.domain.user.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "members")
public class Member extends BaseEntity {

    //고유 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //이메일
    @Column(nullable = false, unique = true)
    private String email;

    //비밀번호
    @Column(nullable = false)
    private String password;

    //사용자 이름
    @Column(nullable = false)
    private String name;

    //전화번호 (선택)
    private String phone;

    //권한: ADMIN / STUDENT
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    //정적 팩토리 메서드
    public static Member of(String email, String password, String name, String phone, Role role) {
        Member member = new Member();
        member.email = email;
        member.password = password;
        member.name = name;
        member.phone = phone;
        member.role = role;
        return member;
    }

    //비밀번호 수정
    public void updatePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    //이름 및 전화번호 수정
    public void updateInfo(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    //관리자 여부 확인
    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }
}
