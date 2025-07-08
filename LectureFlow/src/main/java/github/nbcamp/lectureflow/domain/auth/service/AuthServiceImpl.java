package github.nbcamp.lectureflow.domain.auth.service;

import github.nbcamp.lectureflow.domain.auth.dto.request.SigninRequest;
import github.nbcamp.lectureflow.domain.auth.dto.request.SignupRequest;
import github.nbcamp.lectureflow.domain.auth.dto.response.SigninResponse;
import github.nbcamp.lectureflow.domain.auth.exception.AuthException;
import github.nbcamp.lectureflow.domain.auth.repository.AuthRepository;
import github.nbcamp.lectureflow.global.entity.Member;
import github.nbcamp.lectureflow.global.exception.ErrorCode;
import github.nbcamp.lectureflow.global.filter.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public Long signup(SignupRequest request) {
        if (authRepository.existsByEmail(request.getEmail())) {
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
        return saved.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public SigninResponse signin(SigninRequest request) {
        Member member = authRepository.findByEmail(request.getEmail()).orElseThrow(() -> new AuthException(ErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new AuthException(ErrorCode.PASSWORD_MISMATCH);
        }

        String token = jwtUtil.generateToken(member.getId(), member.getName(), member.getRole());

        return SigninResponse.of(member.getId(), token);
    }
}
