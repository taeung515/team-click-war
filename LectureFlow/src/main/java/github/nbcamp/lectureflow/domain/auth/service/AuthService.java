package github.nbcamp.lectureflow.domain.auth.service;

import github.nbcamp.lectureflow.domain.auth.dto.request.SigninRequest;
import github.nbcamp.lectureflow.domain.auth.dto.request.SignupRequest;
import github.nbcamp.lectureflow.domain.auth.dto.response.SigninResponse;
import github.nbcamp.lectureflow.domain.auth.dto.response.SignupResponse;

public interface AuthService {
    SignupResponse signup(SignupRequest request);

    SigninResponse signin(SigninRequest request);
}
