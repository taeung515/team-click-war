package github.nbcamp.lectureflow.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 공통
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "예상하지 못한 에러"),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "접근권한이 없습니다."),

    // auth
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "사용중인 이메일 입니다."),
    PASSWORD_MISMATCH(HttpStatus.FORBIDDEN, "비밀번호가 일치하지 않습니다."),

    // member
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String msg;

}