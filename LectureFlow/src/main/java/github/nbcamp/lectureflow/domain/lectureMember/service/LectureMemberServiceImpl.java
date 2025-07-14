package github.nbcamp.lectureflow.domain.lectureMember.service;

import github.nbcamp.lectureflow.domain.auth.exception.AuthException;
import github.nbcamp.lectureflow.domain.lecture.exception.LectureException;
import github.nbcamp.lectureflow.domain.lecture.repository.LectureRepository;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureMemberServiceImpl implements LectureMemberService {

    private final MemberRepository memberRepository;
    private final LectureMemberRepository lectureMemberRepository;
    private final LectureRepository lectureRepository;
    private static final Logger log = LoggerFactory.getLogger("LectureMemberServiceImpl.class");
    private static final Marker LECTURE_MEMBER_SUCCESS = MarkerFactory.getMarker("Success");
    private static final Marker LECTURE_MEMBER_FAILED = MarkerFactory.getMarker("Failed");

    @Transactional
    @Override
    public CreateLectureMemberResponse createLectureMember(CreateLectureMemberRequest request, Long memberId) {
        //회원 조회
        Member member = memberRepository.findById(memberId)
                .orElseGet(() ->{
                    log.error(LECTURE_MEMBER_FAILED,"userId: {}와 일치하는 유저가 없습니다",memberId);
                    throw new AuthException(ErrorCode.MEMBER_NOT_FOUND);
                });

        //강의 조회
        Lecture lecture = lectureRepository.findById(request.getLectureId())
                .orElseGet(() -> {
                    log.error(LECTURE_MEMBER_FAILED,"LectureId: {}와 일치하는 강의가 없습니다",request.getLectureId());
                    throw new LectureException(ErrorCode.LECTURE_NOT_FOUND);
                });

        //정원 초과 확인 로직
        boolean overCapacity = checkStudent(lecture.getMaxStudent(), lecture.getId());

        if (overCapacity) {
            log.error(LECTURE_MEMBER_FAILED,"LectureName: {} 강의의 정원이 초과되었습니다",lecture.getLectureName());
            throw new LectureMemberException(ErrorCode.OVER_CAPACITY);
        }

        // 과거 수강 여부 확인
        List<LectureMember> retakeList = checkRetake(memberId, lecture.getId());

        // 이미 재수강 한 경우.(중복 방지)
        if (retakeList.size() >= 2) {
            log.error(LECTURE_MEMBER_FAILED,"LectureName: {}강의는 이미 재수강하였습니다",lecture.getLectureName());
            throw new LectureMemberException(ErrorCode.DUPLICATE_ENROLL);
        }

        LectureMember lectureMember = LectureMember.of(member, lecture);

        //한 번 수강한 적 있으면 재수강
        if(retakeList.size()==1){
            lectureMember.updateRetake();
            log.info(LECTURE_MEMBER_SUCCESS,"LectureName: {} 재수강 처리완료",lectureMember.getLecture().getLectureName());
        }

        //수강 신청
        log.info(LECTURE_MEMBER_SUCCESS,"user: {}의 Lecture: {} 수강 신청이 완료되었습니다.",lectureMember.getMember().getName(),lecture.getLectureName());
        lectureMemberRepository.save(lectureMember);

        return new CreateLectureMemberResponse(lectureMember.getId(), memberId, lecture.getId());
    }

    @Transactional
    @Override
    public void deleteLectureMember(Long LectureMemberId, Long memberId) {

        LectureMember lectureMember = lectureMemberRepository.findById(LectureMemberId)
                .orElseGet(() ->{
                    log.error(LECTURE_MEMBER_FAILED,"userId: {}에 해당하는 유저를 찾을 수 없습니다.",LectureMemberId);
                    throw new LectureMemberException(ErrorCode.LECTURE_MEMBER_NOT_FOUND);
                });

        //로그인한 사용자와 요청한 사용자의 id가 다르면 예외 발생
        if(!lectureMember.getMember().getId().equals(memberId)){
            log.error(LECTURE_MEMBER_FAILED,"로그인한 유저와 요청한 유저의 ID가 일치하지 않습니다.");
            throw new LectureMemberException(ErrorCode.LECTURE_MEMBER_UNAUTHORIZED);
        }
        //해당 수강신청 고유 id 삭제
        log.info(LECTURE_MEMBER_SUCCESS,"userId: {}의 수강신청이 성공적으로 삭제되었습니다.",lectureMember.getId());
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
