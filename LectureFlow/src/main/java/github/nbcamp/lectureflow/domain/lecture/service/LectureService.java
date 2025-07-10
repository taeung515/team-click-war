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

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    @Transactional
    public void createLecture(MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        List<LectureRequestDto> lectureRequestDtoList = ExcelUpload.exelToLecture(inputStream);
        List<Lecture> lectureList = lectureRequestDtoList.stream().map(lectureRequestDto -> Lecture.of(
                lectureRequestDto.getMajorOrGeneral(),
                lectureRequestDto.getDepartment(),
                lectureRequestDto.getGradeLevel(),
                lectureRequestDto.isForeignLanguage(),
                lectureRequestDto.getLectureName(),
                lectureRequestDto.getGrade(),
                lectureRequestDto.getProfessor(),
                lectureRequestDto.getDay(),
                lectureRequestDto.getStartTime(),
                lectureRequestDto.getEndTime(),
                lectureRequestDto.getClassroom(),
                lectureRequestDto.getMaxStudent()
                )).toList();
        lectureRepository.saveAll(lectureList);
    }
}
