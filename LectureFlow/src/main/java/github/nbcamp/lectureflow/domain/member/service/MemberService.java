package github.nbcamp.lectureflow.domain.member.service;

import github.nbcamp.lectureflow.domain.member.dto.request.MemberUpdateRequestDto;
import github.nbcamp.lectureflow.domain.member.dto.request.MemberWithdrawRequestDto;
import github.nbcamp.lectureflow.domain.member.dto.response.MemberResponseDto;
import github.nbcamp.lectureflow.domain.member.exception.MemberException;
import github.nbcamp.lectureflow.domain.member.repository.MemberRepository;
import github.nbcamp.lectureflow.global.entity.Member;
import github.nbcamp.lectureflow.global.exception.ErrorCode;
import github.nbcamp.lectureflow.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //내 정보 조회
    @Transactional(readOnly = true)
    public MemberResponseDto getMyInfo(Long memberId) {
        Member member = getMemberOrThrow(memberId);
        return MemberResponseDto.of(member);
    }

    //회원 정보 수정
    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequestDto requestDto) {
        Member member = getMemberOrThrow(memberId);

        member.updatePassword(passwordEncoder.encode(requestDto.getPassword()));
        member.updateInfo(requestDto.getName(), requestDto.getPhone());
    }

    //회원 탈퇴
    @Transactional
    public void withdrawMember(Long memberId, MemberWithdrawRequestDto requestDto) {
        Member member = getMemberOrThrow(memberId);

        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new MemberException(ErrorCode.PASSWORD_MISMATCH);
        }


    }

    private Member getMemberOrThrow(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
    }
}
