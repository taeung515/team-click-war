package github.nbcamp.lectureflow.domain.auth.service;

import github.nbcamp.lectureflow.domain.auth.dto.request.SigninRequest;
import github.nbcamp.lectureflow.domain.auth.dto.request.SignupRequest;
import github.nbcamp.lectureflow.domain.auth.dto.response.SigninResponse;

public interface AuthService {
    Long signup(SignupRequest request);

    SigninResponse signin(SigninRequest request);
}
