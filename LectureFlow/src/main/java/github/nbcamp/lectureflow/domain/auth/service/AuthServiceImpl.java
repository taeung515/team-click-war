package github.nbcamp.lectureflow.domain.auth.service;

import github.nbcamp.lectureflow.domain.auth.dto.request.SigninRequest;
import github.nbcamp.lectureflow.domain.auth.dto.request.SignupRequest;
import github.nbcamp.lectureflow.domain.auth.dto.response.SigninResponse;
import github.nbcamp.lectureflow.domain.auth.dto.response.SignupResponse;
import github.nbcamp.lectureflow.domain.auth.exception.AuthException;
import github.nbcamp.lectureflow.domain.auth.repository.AuthRepository;
import github.nbcamp.lectureflow.global.entity.Member;
import github.nbcamp.lectureflow.global.exception.ErrorCode;
import github.nbcamp.lectureflow.global.filter.JwtUtil;
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
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private static final Logger log = LoggerFactory.getLogger("AuthServicelmpl.class");
    private static final Marker AUTH_SUCCESS = MarkerFactory.getMarker("Success");
    private static final Marker AUTH_FAILED = MarkerFactory.getMarker("Failed");

    @Override
    @Transactional
    public SignupResponse signup(SignupRequest request) {
        if (authRepository.existsByEmail(request.getEmail())) {
            log.error(AUTH_FAILED,"유저 생성에 실패하였습니다 (중복된 이메일: {})",request.getEmail());
            throw new AuthException(ErrorCode.DUPLICATE_EMAIL);
        }

        Member member = Member.of(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getName(),
                request.getPhone(),
                request.getRole()
        );

        Member saved = authRepository.save(member);
        log.info(AUTH_SUCCESS,"userName: {} 유저가 생성되었습니다",request.getName());
        return SignupResponse.of(saved.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public SigninResponse signin(SigninRequest request) {
        Member member = authRepository.findByEmail(request.getEmail()).orElseThrow(() -> new AuthException(ErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            log.error(AUTH_FAILED,"비밀번호가 일치하지 않아 로그인에 실패하였습니다");
            throw new AuthException(ErrorCode.PASSWORD_MISMATCH);
        }

        String token = jwtUtil.generateToken(member.getId(), member.getName(), member.getRole());
        log.info(AUTH_SUCCESS,"로그인에 성공하였습니다 userId = {}",member.getId());
        return SigninResponse.of(member.getId(), token);
    }
}
