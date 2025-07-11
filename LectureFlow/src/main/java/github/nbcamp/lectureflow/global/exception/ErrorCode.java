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
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),

    // lectureMember(수강신청)
    OVER_CAPACITY(HttpStatus.CONFLICT, "수강 정원이 마감되었습니다."),
    DUPLICATE_ENROLL(HttpStatus.BAD_REQUEST, "이미 수강 또는 재수강 완료한 강의입니다."),
    LECTURE_MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,"수강 신청 내역에 없습니다."),
    LECTURE_MEMBER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"삭제 권한이 없습니다."),


    // lecture
    LECTURE_NOT_FOUND(HttpStatus.NOT_FOUND, "강의를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String msg;
}