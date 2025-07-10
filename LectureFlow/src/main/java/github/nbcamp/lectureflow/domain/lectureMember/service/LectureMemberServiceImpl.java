package github.nbcamp.lectureflow.domain.lectureMember.service;

import github.nbcamp.lectureflow.domain.auth.exception.AuthException;
import github.nbcamp.lectureflow.domain.lecture.LectureRepository;
import github.nbcamp.lectureflow.domain.lectureMember.dto.request.CreateLectureMemberRequest;
import github.nbcamp.lectureflow.domain.lectureMember.dto.response.CreateLectureMemberResponse;
import github.nbcamp.lectureflow.domain.lectureMember.exception.LectureMemberException;
import github.nbcamp.lectureflow.domain.lectureMember.repository.LectureMemberRepository;
import github.nbcamp.lectureflow.domain.member.repository.MemberRepository;
import github.nbcamp.lectureflow.global.entity.Lecture;
import github.nbcamp.lectureflow.global.entity.LectureMember;
import github.nbcamp.lectureflow.global.entity.Member;
import github.nbcamp.lectureflow.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureMemberServiceImpl implements LectureMemberService {

    private final MemberRepository memberRepository;
    private final LectureMemberRepository lectureMemberRepository;
    private final LectureRepository lectureRepository;

    @Transactional
    @Override
    public CreateLectureMemberResponse createLectureMember(CreateLectureMemberRequest request, Long memberId) {
        //회원 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new AuthException(ErrorCode.MEMBER_NOT_FOUND));

        //강의 조회
        Lecture lecture = lectureRepository.findById(Long.valueOf(request.getLectureId()))
                .orElseThrow(() -> new LectureMemberException(ErrorCode.LECTURE_NOT_FOUND));

        //정원 초과 확인 로직
        boolean overCapacity = checkStudent(lecture.getMaxStudent(), lecture.getId());

        if (overCapacity) {
            throw new LectureMemberException(ErrorCode.OVER_CAPACITY);
        }

        // 과거 수강 여부 확인
        List<LectureMember> retakeList = checkRetake(memberId, lecture.getId());

        // 이미 재수강 한 경우.(중복 방지)
        if (retakeList.size() >= 2) {
            throw new LectureMemberException(ErrorCode.DUPLICATE_ENROLL);
        }

        LectureMember lectureMember = LectureMember.of(member, lecture);

        //한 번 수강한 적 있으면 재수강
        if(retakeList.size()==1){
            lectureMember.updateRetake();
        }

        //수강 신청
        lectureMemberRepository.save(lectureMember);

        return new CreateLectureMemberResponse(lectureMember.getId(), memberId, lecture.getId());
    }

    @Transactional
    @Override
    public void deleteLectureMember(Long id, Long memberId) {

        LectureMember lectureMember = lectureMemberRepository.findById(id)
                .orElseThrow(()-> new LectureMemberException(ErrorCode.LECTURE_MEMBER_NOT_FOUND));

        //로그인한 사용자와 요청한 사용자의 id가 다르면 예외 발생
        if(!lectureMember.getMember().getId().equals(memberId)){
            throw new LectureMemberException(ErrorCode.LECTURE_MEMBER_UNAUTHORIZED);
        }
        //해당 수강신청 고유 id 삭제
        lectureMemberRepository.delete(lectureMember);
    }





    //수강 정원 초과 확인 메서드
    public boolean checkStudent(int maxStudent, Long lectureId) {
        return lectureMemberRepository.countByLectureId(lectureId) >= maxStudent;
    }

    //재수강 확인 메서드
    public List<LectureMember> checkRetake(Long memberId, Long lectureId) {
        return lectureMemberRepository.findAllByMemberIdAndLectureId(memberId, lectureId);

    }
}
