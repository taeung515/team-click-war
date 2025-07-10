package github.nbcamp.lectureflow.domain.lecture.service;

import github.nbcamp.lectureflow.domain.lecture.dto.LectureRequestDto;
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
        List<LectureRequestDto.UploadDto> lectureRequestDtoList = ExcelUpload.exelToLecture(inputStream);
        List<Lecture> lectureList = lectureRequestDtoList.stream().map(uploadDto -> Lecture.of(
                uploadDto.getMajorOrGeneral(),
                uploadDto.getDepartment(),
                uploadDto.getGradeLevel(),
                uploadDto.getIsForeignLanguage(),
                uploadDto.getLectureName(),
                uploadDto.getGrade(),
                uploadDto.getProfessor(),
                uploadDto.getDay(),
                uploadDto.getStartTime(),
                uploadDto.getEndTime(),
                uploadDto.getClassroom(),
                uploadDto.getMaxStudent()
        )).toList();
        lectureRepository.saveAll(lectureList);
    }

    public void createLecture(LectureRequestDto.UploadDto uploadDto){
        Lecture lecture = Lecture.of(uploadDto);
        lectureRepository.save(lecture);

    }

    public void updateLecture(LectureRequestDto.UpdateDto updateDto, Long lectureId) {
        Optional<Lecture> optionalLecture = lectureRepository.findById(lectureId);
        /*
        머지 하시면 주석 삭제 예정
        if(optionalLecture.isEmpty())
            throw new LectureException(Errorcode.LECUTRE_NOT_FOUND);
         */
        // 예외처리는 후에 추가할 예정

        Lecture lecture = optionalLecture.get();
        dtoNullIgnoreMapper.updateLecture(updateDto, lecture);


    }

    public void deleteLecture(Long lectureId) {
        // 예외처리는 후에 추가할 예정
        lectureRepository.deleteById(lectureId);
    }
}
