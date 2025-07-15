package github.nbcamp.lectureflow.domain.lecture.service;

import github.nbcamp.lectureflow.domain.lecture.dto.request.LectureUpdateRequestDto;
import github.nbcamp.lectureflow.domain.lecture.dto.request.LectureUploadRequestDto;
import github.nbcamp.lectureflow.domain.lecture.exception.LectureException;
import github.nbcamp.lectureflow.domain.lecture.repository.LectureRepository;
import github.nbcamp.lectureflow.domain.lectureMember.service.LectureMemberService;
import github.nbcamp.lectureflow.global.entity.Lecture;
import github.nbcamp.lectureflow.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LectureService {

    private static final Logger log = LoggerFactory.getLogger("LectureService.class");
    private static final Marker LECTURE_SERVICE_SUCCESS = MarkerFactory.getMarker("Success");
    private static final Marker LECTURE_SERVICE_FAILED = MarkerFactory.getMarker("Failed");
    private final LectureRepository lectureRepository;
    private final DtoNullIgnoreMapper dtoNullIgnoreMapper;
    private final LectureMemberService lectureMemberService;

    public void createLectures(MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        List<LectureUploadRequestDto> lectureRequestDtoList = FileUpload.fileToLecture(inputStream);
        List<Lecture> lectureList = lectureRequestDtoList.stream().map(lectureUploadRequestDto -> Lecture.of(
                lectureUploadRequestDto.getMajorOrGeneral(),
                lectureUploadRequestDto.getDepartment(),
                lectureUploadRequestDto.getGradeLevel(),
                lectureUploadRequestDto.getIsForeignLanguage(),
                lectureUploadRequestDto.getLectureName(),
                lectureUploadRequestDto.getGrade(),
                lectureUploadRequestDto.getProfessor(),
                lectureUploadRequestDto.getDay(),
                lectureUploadRequestDto.getStartTime(),
                lectureUploadRequestDto.getEndTime(),
                lectureUploadRequestDto.getClassroom(),
                lectureUploadRequestDto.getMaxStudent()
        )).toList();
        log.info(LECTURE_SERVICE_SUCCESS, "Lectures: {}가 생성되었습니다.",
                lectureList.stream()
                        .map(Lecture::getLectureName)
                        .collect(Collectors.joining(", "))
        );
        lectureRepository.createLectures(lectureList);
    }

    public void createLecture(LectureUploadRequestDto lectureUploadRequestDto) {
        Lecture lecture = Lecture.of(lectureUploadRequestDto);
        log.info(LECTURE_SERVICE_SUCCESS, "Lecture: {}가 생성되었습니다.", lecture.getLectureName());
        lectureRepository.save(lecture);

    }

    public void updateLecture(LectureUpdateRequestDto lectureUpdateRequestDto, Long lectureId) {
        Optional<Lecture> optionalLecture = lectureRepository.findById(lectureId);

        // 값 존재 하지 않는 예외
        if (optionalLecture.isEmpty())
            throw new LectureException(ErrorCode.LECTURE_NOT_FOUND);

        Lecture lecture = optionalLecture.get();
        log.info(LECTURE_SERVICE_SUCCESS, "Lecture: {}가 수정되었습니다.", lecture.getLectureName());
        dtoNullIgnoreMapper.updateLecture(lectureUpdateRequestDto, lecture);


    }

    public void deleteLecture(Long lectureId) {
        //해당 데이터 존재 여부 확인
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() -> new LectureException(ErrorCode.LECTURE_NOT_FOUND));
        lectureMemberService.deleteLectureMemberByLecture(lecture); // 연관관계 삭제
        log.info(LECTURE_SERVICE_FAILED, "LectureId: {}가 삭제되었습니다.", lectureId);
        lectureRepository.deleteById(lectureId);
    }
}
