package github.nbcamp.lectureflow.domain.auth.service;

import github.nbcamp.lectureflow.domain.auth.dto.request.SigninRequest;
import github.nbcamp.lectureflow.domain.auth.dto.request.SignupRequest;
import github.nbcamp.lectureflow.domain.auth.dto.response.SigninResponse;
import github.nbcamp.lectureflow.domain.auth.exception.AuthException;
import github.nbcamp.lectureflow.domain.auth.repository.AuthRepository;
import github.nbcamp.lectureflow.domain.user.enums.Role;
import github.nbcamp.lectureflow.global.entity.Member;
import github.nbcamp.lectureflow.global.filter.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;
    @Mock
    private AuthRepository authRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtUtil jwtUtil;

    @Test
    void 회원가입에_성공한다() {
        // given
        SignupRequest request = new SignupRequest("test@email.com", "testPassword", "testName", "010-1234-1234", Role.STUDENT);
        Member member = mock();
        ReflectionTestUtils.setField(member, "id", 1L);
        given(authRepository.save(any())).willReturn(member);
        given(passwordEncoder.encode(anyString())).willReturn("encodedPassword");
        // when
        Long signup = authService.signup(request);
        // then
        assertNotNull(signup);
    }

    @Test
    void 로그인에_성공한다() {
        // given
        SigninRequest request = new SigninRequest("test@email.com", "testPassword");
        Member member = Member.of("test@email.com", "encodedPassword", "testName", "010-1234-1234", Role.STUDENT);
        ReflectionTestUtils.setField(member, "id", 1L);

        given(authRepository.findByEmail(request.getEmail())).willReturn(Optional.of(member));
        given(passwordEncoder.matches(request.getPassword(), member.getPassword())).willReturn(true);
        given(jwtUtil.generateToken(member.getId(), member.getName(), member.getRole())).willReturn("testToken");

        // when
        SigninResponse response = authService.signin(request);

        // then
        assertNotNull(response);
        assertEquals(1L, response.getMemberId());
        assertEquals("testToken", response.getToken());
    }

    @Test
    void 이미_등록된_이메일로_회원가입하면_예외가_발생한다() {
        // given
        SignupRequest request = new SignupRequest("test@email.com", "testPassword", "testName", "010-1234-1234", Role.STUDENT);
        given(authRepository.existsByEmail(request.getEmail())).willReturn(true);

        // when & then
        assertThrows(AuthException.class, () -> authService.signup(request));
    }

    @Test
    void 이메일로_회원_조회시_존재하지_않으면_예외가_발생한다() {
        // given
        SigninRequest request = new SigninRequest("notfound@email.com", "anyPassword");
        given(authRepository.findByEmail(request.getEmail())).willReturn(Optional.empty());

        // when & then
        assertThrows(AuthException.class, () -> authService.signin(request));
    }

}