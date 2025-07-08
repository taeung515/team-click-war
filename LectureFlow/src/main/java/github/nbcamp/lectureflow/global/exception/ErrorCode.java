package github.nbcamp.lectureflow.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 공통
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "예상하지 못한 에러"),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "접근권한이 없습니다.");

    private final HttpStatus status;
    private final String msg;

}