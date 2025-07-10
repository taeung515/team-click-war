package github.nbcamp.lectureflow.domain.lecture.service;

import github.nbcamp.lectureflow.domain.lecture.dto.LectureRequestDto;
import github.nbcamp.lectureflow.global.entity.Lecture;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

//componentModel로 스프링 빈 등록
//null일 경우 무시하도록
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DtoNullIgnoreMapper {
    void updateLecture(LectureRequestDto.UpdateDto updateDto, @MappingTarget Lecture lecture);

}
