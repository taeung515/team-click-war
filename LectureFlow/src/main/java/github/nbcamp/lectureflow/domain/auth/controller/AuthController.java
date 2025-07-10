package github.nbcamp.lectureflow.domain.auth.controller;


import github.nbcamp.lectureflow.domain.auth.dto.request.SigninRequest;
import github.nbcamp.lectureflow.domain.auth.dto.request.SignupRequest;
import github.nbcamp.lectureflow.domain.auth.dto.response.SigninResponse;
import github.nbcamp.lectureflow.domain.auth.dto.response.SignupResponse;
import github.nbcamp.lectureflow.domain.auth.service.AuthService;
import github.nbcamp.lectureflow.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponse>> signup(@Valid @RequestBody SignupRequest request) {
        SignupResponse response = authService.signup(request);
        return ResponseEntity.ok(ApiResponse.success("회원가입 성공", response));
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<SigninResponse>> signin(@Valid @RequestBody SigninRequest request) {
        SigninResponse response = authService.signin(request);
        return ResponseEntity.ok(ApiResponse.success("로그인 성공", response));
    }
}
