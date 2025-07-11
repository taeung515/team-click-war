package github.nbcamp.lectureflow.domain.lecture.service;

import github.nbcamp.lectureflow.domain.lecture.dto.request.LectureUploadRequestDto;
import github.nbcamp.lectureflow.domain.lecture.exception.LectureException;
import github.nbcamp.lectureflow.global.enums.Day;
import github.nbcamp.lectureflow.global.enums.Department;
import github.nbcamp.lectureflow.global.enums.MajorOrGeneral;
import github.nbcamp.lectureflow.global.exception.ErrorCode;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.io.InputStream;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUpload {
    public static List<LectureUploadRequestDto> exelToLecture(InputStream inputStream) {

        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            int rowNumber = 0;
            List<LectureUploadRequestDto> lectureList = new ArrayList<>();

            while (rowIterator.hasNext()) {

                Row nowRow = rowIterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellIterator = nowRow.iterator();
                int cellIndex = 0;

                LectureUploadRequestDto.LectureUploadRequestDtoBuilder lectureRequestDtoBuilder = LectureUploadRequestDto.builder();

                while (cellIterator.hasNext()) {
                    Cell nowCell = cellIterator.next();
                    try {
                        switch (cellIndex) {
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
                                lectureRequestDtoBuilder.gradeLevel((int) gradeLevel);
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
                                lectureRequestDtoBuilder.grade((int) grade);
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
                                lectureRequestDtoBuilder.classroom((int) classroom);
                                break;
                            case 11:
                                double maxStudent = nowCell.getNumericCellValue();
                                lectureRequestDtoBuilder.maxStudent((int) maxStudent);
                                break;
                        }
                    } catch (IllegalArgumentException e) {
                        // 잘못된 셀 값을 가진 경우: 존재하는 enum의 상수 외의 값이 들어있는 경우 등...
                        throw new LectureException(ErrorCode.WRONG_REQUEST_VALUE);
                    } catch (IllegalStateException e) {
                        // 잘못된 셀 값 형식을 가진 경우: String 타입에 Boolean 타입을 받은 경우 등...
                        throw new LectureException(ErrorCode.WRONG_REQUEST_TYPE);
                    } catch (DateTimeException e) {
                        // 잘못된 날짜 형식(09:72)일 경우
                        throw new LectureException(ErrorCode.WRONG_TIME_FORM);
                    }
                    cellIndex++;
                }
                lectureList.add(lectureRequestDtoBuilder.build());
            }
            return lectureList;
        } catch (IOException e) {
            // IOStream
            throw new LectureException(ErrorCode.CANT_UPLOAD_LECTURE);
        } catch (NotOfficeXmlFileException e) {
            //다른 확장자 파일
            throw new LectureException(ErrorCode.WRONG_TYPE_FILE);
        }
    }
}
