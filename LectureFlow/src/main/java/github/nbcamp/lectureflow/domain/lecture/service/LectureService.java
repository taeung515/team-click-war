package github.nbcamp.lectureflow.domain.lecture.service;

import github.nbcamp.lectureflow.domain.lecture.dto.request.LectureUpdateRequestDto;
import github.nbcamp.lectureflow.domain.lecture.dto.request.LectureUploadRequestDto;
import github.nbcamp.lectureflow.domain.lecture.repository.LectureRepository;
import github.nbcamp.lectureflow.global.entity.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LectureService {

    private final LectureRepository lectureRepository;
    private final DtoNullIgnoreMapper dtoNullIgnoreMapper;

    public void createLectures(MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        List<LectureUploadRequestDto> lectureRequestDtoList = ExcelUpload.exelToLecture(inputStream);
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
        lectureRepository.saveAll(lectureList);
    }

    public void createLecture(LectureUploadRequestDto lectureUploadRequestDto){
        Lecture lecture = Lecture.of(lectureUploadRequestDto);
        lectureRepository.save(lecture);

    }

    public void updateLecture(LectureUpdateRequestDto lectureUpdateRequestDto, Long lectureId) {
        Optional<Lecture> optionalLecture = lectureRepository.findById(lectureId);
        /*
        머지 하시면 주석 삭제 예정
        if(optionalLecture.isEmpty())
            throw new LectureException(Errorcode.LECUTRE_NOT_FOUND);
         */
        // 예외처리는 후에 추가할 예정

        Lecture lecture = optionalLecture.get();
        dtoNullIgnoreMapper.updateLecture(lectureUpdateRequestDto, lecture);


    }

    public void deleteLecture(Long lectureId) {
        // 예외처리는 후에 추가할 예정
        lectureRepository.deleteById(lectureId);
    }
}
