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

    // lecture
    LECTURE_NOT_FOUND(HttpStatus.NOT_FOUND, "강의를 찾을 수 없습니다."),
    CANT_UPLOAD_LECTURE(HttpStatus.BAD_REQUEST, "파일을 업로드할 수 없습니다."),
    WRONG_REQUEST_VALUE(HttpStatus.BAD_REQUEST, "파일의 셀 값이 올바르지 않습니다."),
    WRONG_REQUEST_TYPE(HttpStatus.BAD_REQUEST, "파일의 셀 값의 형식이 올바르지 않습니다."),
    TOO_BIG_FILE(HttpStatus.BAD_REQUEST, "파일의 크기가 너무 큽니다."),
    WRONG_TYPE_FILE(HttpStatus.BAD_REQUEST, "처리할 수 없는 파일 형식입니다."),
    WRONG_TIME_FORM(HttpStatus.BAD_REQUEST, "잘못된 형태의 시간 값입니다."),
    NULL_CELL(HttpStatus.BAD_REQUEST, "빈 셀 값이 존재합니다.");

    private final HttpStatus status;
    private final String msg;
}