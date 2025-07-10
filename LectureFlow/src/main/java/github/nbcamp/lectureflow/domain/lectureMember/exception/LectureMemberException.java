package github.nbcamp.lectureflow.domain.lectureMember.exception;

import github.nbcamp.lectureflow.global.exception.CustomException;
import github.nbcamp.lectureflow.global.exception.ErrorCode;

public class LectureMemberException extends CustomException {
    public LectureMemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}
