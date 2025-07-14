package github.nbcamp.lectureflow.domain.member.service;

import github.nbcamp.lectureflow.domain.member.dto.request.MemberUpdateRequestDto;
import github.nbcamp.lectureflow.domain.member.dto.request.MemberWithdrawRequestDto;
import github.nbcamp.lectureflow.domain.member.dto.response.MemberResponseDto;
import github.nbcamp.lectureflow.domain.member.exception.MemberException;
import github.nbcamp.lectureflow.domain.member.repository.MemberRepository;
import github.nbcamp.lectureflow.global.entity.Member;
import github.nbcamp.lectureflow.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger("MemberService.class");
    private static final Marker MEMBER_SUCCESS = MarkerFactory.getMarker("Success");
    private static final Marker MEMBER_FAILED = MarkerFactory.getMarker("Failed");

    //내 정보 조회
    @Transactional(readOnly = true)
    public MemberResponseDto getMyInfo(Long memberId) {
        Member member = getMemberOrThrow(memberId);
        log.info(MEMBER_SUCCESS,"userId: {}의 정보 조회가 완료되었습니다", memberId);
        return MemberResponseDto.of(member);
    }

    //회원 정보 수정
    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequestDto requestDto) {
        Member member = getMemberOrThrow(memberId);

        member.updatePassword(passwordEncoder.encode(requestDto.getPassword()));
        member.updateInfo(requestDto.getName(), requestDto.getPhone());
        log.info(MEMBER_SUCCESS,"userId: {}의 정보가 수정되었습니다",memberId);
    }

    //회원 탈퇴
    @Transactional
    public void withdrawMember(Long memberId, MemberWithdrawRequestDto requestDto) {
        Member member = getMemberOrThrow(memberId);

        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            log.error(MEMBER_FAILED,"비밀번호가 일치하지 않아 회원 탈퇴 실패");
            throw new MemberException(ErrorCode.PASSWORD_MISMATCH);
        }


    }

    private Member getMemberOrThrow(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
    }
}
