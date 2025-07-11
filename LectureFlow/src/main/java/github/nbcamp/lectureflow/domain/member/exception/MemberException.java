package github.nbcamp.lectureflow.domain.member.exception;

import github.nbcamp.lectureflow.global.exception.CustomException;
import github.nbcamp.lectureflow.global.exception.ErrorCode;

public class MemberException extends CustomException {
    public MemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}
