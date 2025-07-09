package github.nbcamp.lectureflow.domain.auth.exception;

import github.nbcamp.lectureflow.global.exception.CustomException;
import github.nbcamp.lectureflow.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AuthException extends CustomException {
    public AuthException(ErrorCode errorCode) {
        super(errorCode);
    }
}
