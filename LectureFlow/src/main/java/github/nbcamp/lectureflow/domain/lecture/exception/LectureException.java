package github.nbcamp.lectureflow.domain.lecture.exception;

import github.nbcamp.lectureflow.global.exception.CustomException;
import github.nbcamp.lectureflow.global.exception.ErrorCode;

public class LectureException extends CustomException {
    public LectureException(ErrorCode errorCode) {
        super(errorCode);
    }
}
