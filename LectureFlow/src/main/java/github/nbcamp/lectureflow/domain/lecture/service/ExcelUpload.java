package github.nbcamp.lectureflow.domain.lecture.service;

import github.nbcamp.lectureflow.domain.lecture.dto.LectureRequestDto;
import github.nbcamp.lectureflow.global.entity.Lecture;
import github.nbcamp.lectureflow.global.enums.Day;
import github.nbcamp.lectureflow.global.enums.Department;
import github.nbcamp.lectureflow.global.enums.MajorOrGeneral;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUpload {
    public static List<LectureRequestDto> exelToLecture(InputStream inputStream){

        try{
            // 엑셀 파일 읽어서 Workbook 객체로 만듦
            Workbook workbook = new XSSFWorkbook(inputStream);
            // 첫 번째 시트를 가져옴(시트가 하나라고 가정하고 진행할 예정이기 때문에 인덱스 0 고정)
            Sheet sheet = workbook.getSheetAt(0);
            // Row를 처리할 iterator 생성(반복자)
            Iterator<Row> rowIterator = sheet.iterator();
            int rowNumber = 0;  // 0은 헤더이기 때문에 스킵
            //결과 담을 리스트
            List<LectureRequestDto> lectureList = new ArrayList<>();

            //다음 열이 존재하지 않으면 멈춤
            while(rowIterator.hasNext()){
                // 행 하나를 꺼내서 저장
                Row nowRow = rowIterator.next();
                if(rowNumber == 0){
                    rowNumber++;
                    continue;
                }
                //Cell을 처리할 iterator 생성(반복자)
                Iterator<Cell> cellIterator = nowRow.iterator();
                int cellIndex = 0;
                //빌더
                LectureRequestDto.LectureRequestDtoBuilder lectureRequestDtoBuilder = LectureRequestDto.builder();

                // 다음 셀이 존재하지 않으면 멈춤
                while(cellIterator.hasNext()){
                    // 셀 하나를 꺼내서 저장
                    Cell nowCell = cellIterator.next();
                    switch(cellIndex){
                        case 0:
                            String uploadMajorOrGeneral = nowCell.getStringCellValue();
                            lectureRequestDtoBuilder.majorOrGeneral(MajorOrGeneral.valueOf(uploadMajorOrGeneral));
                            break;
                        case 1:
                            String department = nowCell.getStringCellValue();
                            lectureRequestDtoBuilder.department(Department.valueOf(department));
                            break;
                        case 2:
                            double gradeLevel = nowCell.getNumericCellValue();
                            lectureRequestDtoBuilder.gradeLevel((int)gradeLevel);
                            break;
                        case 3:
                            boolean isForeignLanguage = nowCell.getBooleanCellValue();
                            lectureRequestDtoBuilder.isForeignLanguage(isForeignLanguage);
                            break;
                        case 4:
                            String lectureName = nowCell.getStringCellValue();
                            lectureRequestDtoBuilder.lectureName(lectureName);
                            break;
                        case 5:
                            double grade = nowCell.getNumericCellValue();
                            lectureRequestDtoBuilder.grade((int)grade);
                            break;
                        case 6:
                            String professor = nowCell.getStringCellValue();
                            lectureRequestDtoBuilder.professor(professor);
                            break;
                        case 7:
                            String day = nowCell.getStringCellValue();
                            lectureRequestDtoBuilder.day(Day.valueOf(day));
                            break;
                        case 8:
                             String startTime = nowCell.getStringCellValue();
                            lectureRequestDtoBuilder.startTime(LocalTime.parse(startTime));
                            break;
                        case 9:
                             String endTime = nowCell.getStringCellValue();
                            lectureRequestDtoBuilder.endTime(LocalTime.parse(endTime));
                            break;
                        case 10:
                            double classroom = nowCell.getNumericCellValue();
                            lectureRequestDtoBuilder.classroom((int)classroom);
                            break;
                        case 11:
                            double maxStudent = nowCell.getNumericCellValue();
                            lectureRequestDtoBuilder.maxStudent((int)maxStudent);
                            break;
                    }
                    cellIndex++;
                }
                lectureList.add(lectureRequestDtoBuilder.build());
            }
            return lectureList;
        }catch(IOException e){
            throw new RuntimeException("예외처리 예정이용");
        }
    }
}
