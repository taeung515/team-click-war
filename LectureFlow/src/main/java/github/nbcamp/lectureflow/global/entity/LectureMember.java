package github.nbcamp.lectureflow.global.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //수강신청 내역 고유 ID
    @Column(name = "lecture_member_id", nullable = false)
    private Long lectureMemberId;

    //학생(member)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    //강의 (lecture)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", nullable = false)
    private Lecture lecture;

    // 재수강 여부
    @Column(nullable=false)
    private boolean isRetake;

    /**
     * 정적 팩토리 메서드
     * 재수강 여부는 기본적으로 false.
     */
    public static LectureMember of(Member member, Lecture lecture) {
        LectureMember lectureMember = new LectureMember();
        lectureMember.member = member;
        lectureMember.lecture = lecture;
        lectureMember.isRetake=false;
        return lectureMember;
    }

    /**
     *     재수강인 경우 true로 변경(default false)
     */
    public void updateRetake(){
        this.isRetake=true;
    }
}