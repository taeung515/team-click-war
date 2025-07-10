package github.nbcamp.lectureflow.global.Response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum LectureResponse {
    Lecture_UPLOAD_SUCCESS(HttpStatus.CREATED, "강의를 성공적으로 업로드했습니다."),
    LECTURE_UPLOAD_FAIL(HttpStatus.BAD_REQUEST, "강의 업로드에 실패했습니다."),
    LECTURE_UPDATE_SUCCESS(HttpStatus.OK, "강의를 성공적으로 수정했습니다."),
    LECTURE_UPDATE_FAIL(HttpStatus.BAD_REQUEST, "강의 수정에 실패했습니다."),
    LECTURE_DELETE_SUCCESS(HttpStatus.OK, "강의를 성공적으로 삭제했습니다."),
    LECTURE_DELETE_FAIL(HttpStatus.BAD_REQUEST, "강의 삭제에 실패했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    LectureResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
