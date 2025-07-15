# 👆클릭 전쟁[click war]
<br/>

## 📚1. 프로젝트 소개
강의 등록부터 실시간 수강신청까지, 성능과 동시성, 사용자/관리자 편의성에 집중한 수강신청 시스템입니다.

<br/><br/>
## 2. 🪄기능 소개
- 실시간 수강신청 상황에서도 안정적인 처리를 위한 동시성 제어 설계
- 관리자/학생 역할에 따라 다른 기능 제공
- 인기 검색어 캐시, JWT 기반 인증, Restful API 적용
- excel 수강신청 목록 업로드 기능 지원
- 기능 지속 확장 예정

 <br/><br/> 
## 3. 🛠️기술 스택
### Language / Framework
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"/>


### DB / Cache
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/redis-FF4438?style=for-the-badge&logo=redis&logoColor=white"/>

### infra / CI-CD
<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"> <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/github actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white"/>


### Test
<img src="https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white"/> <img src="https://img.shields.io/badge/apache Jmeter-D22128?style=for-the-badge&logo=apacheJmeter&logoColor=white"/>

### Version Control / Collaboration
<img src="https://img.shields.io/badge/Jira-0052CC?style=for-the-badge&logo=jira&logoColor=white"/> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white"> <img src="https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white">

<br/><br/>

## 4. 📁 프로젝트 구조
<pre>
lectureflow
├── domain
│   ├── auth
│   │   ├── controller
│   │   ├── dto
│   │   │   ├── request
│   │   │   └── response
│   │   ├── exception
│   │   ├── repository
│   │   └── service
│   ├── keyword
│   │   ├── controller
│   │   ├── dto
│   │   ├── repository
│   │   └── service
│   ├── lecture
│   │   ├── controller
│   │   ├── dto
│   │   │   ├── request
│   │   │   └── response
│   │   ├── enums
│   │   ├── exception
│   │   ├── repository
│   │   └── service
│   ├── lectureMember
│   │   ├── controller
│   │   ├── dto
│   │   │   ├── request
│   │   │   └── response
│   │   ├── exception
│   │   ├── repository
│   │   └── service
│   ├── member
│   │   ├── controller
│   │   ├── dto
│   │   │   ├── request
│   │   │   └── response
│   │   ├── enums
│   │   ├── exception
│   │   ├── repository
│   │   └── service
│   ├── response
│   └── user
│       └── enums
├── global
│   ├── config
│   ├── dto
│   ├── entity
│   ├── exception
│   └── filter
└──
</pre>
<br/><br/>
## 5. 💻공통 응답 객체 / git 컨벤션

### commit message convention
| 태그 | 설명 |
| --- | --- | 
| Feat | 새로운 파일 및 기능 생성 |
| Imp | 기존 기능 수정 |
| Fix | 오류 수정 |
| Refactor | 리팩토링 -> 기능 변경 없이 코드 개선 |
| Docs | 주석 및 ReadMe 생성 |
| Chore | 그 외 기능 |
| Style | 코드 스타일 수정 (띄어쓰기, import 정리 등)

### 공통 응답 객체

```
@Getter
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    private ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null);
    }

    public static <T> ApiResponse<T> error(T data) {
        return new ApiResponse<>(false, "요청실패", data);
    }
}
```

[✏️코드 컨벤션 자세히 보기(노션으로 이동)](https://www.notion.so/teamsparta/2292dc3ef514805ca15de350977254b2)   


<br/><br/>
## 6. 📜wireframe/ERD/API 명세

<details><summary>wireframe
</summary>
<img width="977" height="581" alt="image" src="https://github.com/user-attachments/assets/0d0ac902-6a82-4573-88e6-e7c0f759bee9" />
</details>
<details><summary>ERD
</summary>
<img width="1888" height="1196" alt="lecture" src="https://github.com/user-attachments/assets/89b8d220-40bb-4e1f-89bf-751032edfc95" />
</details>

<details><summary>API 명세서
</summary>
 
 ## 🔐Auth

| Method | URL          | 설명         | Request 예시 | Response 예시|
|--------|--------------|--------------|---------|----------|
| POST   | /auth/signup | 회원가입  | {<br> "email": "student@example.com", <br> "password": "testPassword456!", <br> "name": "홍길동",<br> "phone": "010-1234-5678", <br> "role": "STUDENT"<br>} <br> | {<br>"success": true, <br>"message": "회원가입 성공",<br>"data": {<br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "memberId": 1<br>&nbsp;&nbsp;&nbsp;&nbsp;},<br>"timestamp": "2025-07-09T11:17:15.0377381"<br>}|
| POST | /auth/signin | 로그인  | {<br> "email": "student@example.com",<br> "password": "testPassword123!"<br>} | {<br> "success": true,<br> "message": "로그인 성공",<br> "data": {<br> &nbsp;&nbsp;"memberId": 1,<br> &nbsp;&nbsp;"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLtmY3quLjrj5kiLCJtZW1iZXJJZCI6MSwiYXV0aCI6IlNUVURFTlQiLCJleHAiOjE3NTIxMTQxODgsImlhdCI6MTc1MjAyNzc4OH0.COQn16rWAvXqdo0wfijWIgRt7i-56avIq_Lyo_zX9HM"<br> &nbsp;&nbsp;&nbsp; },<br> "timestamp": "2025-07-09T11:23:08.9962236"<br>} |

<br><br>
## 👤Member
| Method | URL          | 설명         | Request 예시 | Response 예시|
|--------|--------------|--------------|---------|----------|
| GET | /members/me | 내 정보 조회 |  | {<br> "success": true,<br> "message": "내 정보 조회 성공",<br> "data": {<br> &nbsp;&nbsp;"memberId": 1,<br> &nbsp;&nbsp;"email": "student@naver.com",<br> &nbsp;&nbsp;"name": "윤호준",<br> &nbsp;&nbsp;"phone": "010-1234-5678",<br> &nbsp;&nbsp;"role": "STUDENT"<br> },<br> "timestamp": "2025-07-09T12:10:00.123456"<br>} |
| PUT | /members/me | 회원 정보 수정 | {<br> "name": "윤호준",<br> "phone": "010-1234-5678",<br> "password": "Pw1235678!"<br>} | {<br> "success": true,<br> "message": "회원 정보 수정 완료",<br> "data": null,<br> "timestamp": "2025-07-09T12:10:00.123456"<br>} |
| DELETE | /members/withdraw | 회원 탈퇴 | {<br> "password": "Password123!"<br>} | {<br> "success": true",<br> "message": "회원 탈퇴 완료",<br> "data": null,<br> "timestamp": "2025-07-09T12:10:00.123456"<br>} |

<br><br>
## 🗂️Keyword
| Method | URL          | 설명         | Request 예시 | Response 예시|
|--------|--------------|--------------|---------|----------|
| GET | /keywords/v1/popular | 인기 검색어 상위 10개 조회 | | {<br> "success": true,<br> "message": "탑 10 인기 검색어 조회 성공",<br> "data": {<br> &nbsp;&nbsp;"topTenKeywords": [<br> &nbsp;&nbsp;&nbsp;&nbsp;"과학",<br> &nbsp;&nbsp;&nbsp;&nbsp;"인공지능",<br> &nbsp;&nbsp;&nbsp;&nbsp;"데이터베이스",<br> &nbsp;&nbsp;&nbsp;&nbsp;"자바",<br> &nbsp;&nbsp;&nbsp;&nbsp;"쿠버네티스",<br> &nbsp;&nbsp;&nbsp;&nbsp;"리액트",<br> &nbsp;&nbsp;&nbsp;&nbsp;"알고리즘",<br> &nbsp;&nbsp;&nbsp;&nbsp;"파이썬",<br> &nbsp;&nbsp;&nbsp;&nbsp;"마이크로서비스",<br> &nbsp;&nbsp;&nbsp;&nbsp;"Redis"<br> &nbsp;&nbsp;]<br> },<br> "timestamp": "2025-07-14T19:29:44.1534889"<br>} |
| GET | /keywords/v2/popular | 인기 검색어 상위 10개 조회 v2 캐시 사용 | | {<br> "success": true,<br> "message": "탑 10 인기 검색어 조회 성공",<br> "data": {<br> &nbsp;&nbsp;"topTenKeywords": [<br> &nbsp;&nbsp;&nbsp;&nbsp;"과학",<br> &nbsp;&nbsp;&nbsp;&nbsp;"인공지능",<br> &nbsp;&nbsp;&nbsp;&nbsp;"데이터베이스",<br> &nbsp;&nbsp;&nbsp;&nbsp;"자바",<br> &nbsp;&nbsp;&nbsp;&nbsp;"쿠버네티스",<br> &nbsp;&nbsp;&nbsp;&nbsp;"리액트",<br> &nbsp;&nbsp;&nbsp;&nbsp;"알고리즘",<br> &nbsp;&nbsp;&nbsp;&nbsp;"파이썬",<br> &nbsp;&nbsp;&nbsp;&nbsp;"마이크로서비스",<br> &nbsp;&nbsp;&nbsp;&nbsp;"Redis"<br> &nbsp;&nbsp;]<br> },<br> "timestamp": "2025-07-14T19:29:44.1534889"<br>} |


<br><br>
## 🗣️Lecture
| Method | URL          | 설명         | Request 예시 | Response 예시|
|--------|--------------|--------------|---------|----------|
| POST | /admin/lectures/upload | 강의 엑셀 업로드 등록 | .xlsx 파일 업로드 | {<br> "success": true,<br> "message": "강의를 성공적으로 업로드했습니다.",<br> "data": null,<br> "timestamp": "2025-07-09T11:17:15.0377381"<br>} |
| POST | /admin/lectures | 강의 단건 등록 | {<br> "department": "COMPUTER_SCIENCE_ENGINEERING",<br> "gradeLevel": 2,<br> "isForeignLanguage": false,<br> "lectureName": "자료구조",<br> "grade": 3,<br> "professor": "김철수",<br> "day": "TUE",<br> "startTime": "13:00:00",<br> "endTime": "15:00:00",<br> "classroom": 1301,<br> "maxStudent": 40<br>} | {<br> "success": true,<br> "message": "강의를 성공적으로 업로드했습니다.",<br> "data": null,<br> "timestamp": "2025-07-09T11:17:15.0377381"<br>} |
| PATCH | /admin/lectures/{lectureId} | 개별 강의 수정 | {<br> "department": "COMPUTER_SCIENCE_ENGINEERING",<br> "professor": "김영희"<br>} | {<br> "success": true,<br> "message": "강의를 성공적으로 수정했습니다.",<br> "data": null,<br> "timestamp": "2025-06-18T10:45:33Z"<br>} |
| DELETE | /admin/lectures/{lectureId} | 개별 강의 삭제 |  | {<br> "success": true,<br> "message": "강의 삭제",<br> "data": null,<br> "timestamp": "2025-07-09T11:17:15.0377381"<br>} |
| GET | /lectures/{lectureId} | 강의 단건 조회 |  | {<br> "success": true,<br> "message": "Lecture 조회 성공",<br> "data": {<br> &nbsp;&nbsp;"lectureId": 1,<br> &nbsp;&nbsp;"majorOrGeneral": "MAJOR",<br> &nbsp;&nbsp;"department": "COMPUTER_SCIENCE_ENGINEERING",<br> &nbsp;&nbsp;"gradeLevel": 1,<br> &nbsp;&nbsp;"lectureName": "컴퓨터개론",<br> &nbsp;&nbsp;"grade": 1,<br> &nbsp;&nbsp;"professor": "김철수",<br> &nbsp;&nbsp;"day": "MON",<br> &nbsp;&nbsp;"startTime": "09:00",<br> &nbsp;&nbsp;"endTime": "10:15",<br> &nbsp;&nbsp;"classroom": "101",<br> &nbsp;&nbsp;"maxStudent": 40,<br> &nbsp;&nbsp;"foreignLanguage": false<br> },<br> "timestamp": "2025-07-10T12:17:51.8790942"<br>} |
| GET | /lectures/search?keyword=value&majorOrGeneral=MAJOR,GENERAL | 조건에 따른 강의 목록 조회 | | {<br> "success": true,<br> "message": "Lectures 조회 성공",<br> "data": {<br> &nbsp;&nbsp;"content": [ ... 5개 강의 객체 ... ],<br> &nbsp;&nbsp;"page": {<br> &nbsp;&nbsp;&nbsp;&nbsp;"size": 5,<br> &nbsp;&nbsp;&nbsp;&nbsp;"number": 0,<br> &nbsp;&nbsp;&nbsp;&nbsp;"totalElements": 5,<br> &nbsp;&nbsp;&nbsp;&nbsp;"totalPages": 1<br> &nbsp;&nbsp;}<br> },<br> "timestamp": "2025-07-10T17:01:40.3826776"<br>} |


<br><br>
## ✏️LectureMember
| Method | URL          | 설명         | Request 예시 | Response 예시|
|--------|--------------|--------------|---------|----------|
| POST | /lectures/enroll | 수강신청 | {<br> "lectureId": 4<br>} | {<br> "success": true,<br> "message": "수강신청 완료",<br> "data": {<br> &nbsp;&nbsp;"lectureMemberId": 2,<br> &nbsp;&nbsp;"memberId": 4,<br> &nbsp;&nbsp;"lectureId": 5<br> },<br> "timestamp": "2025-07-09T11:17:15.0377381"<br>} |
| GET | /lectures/enroll | 로그인한 사용자의 수강 과목 전체 조회 |  | {<br> "success": true,<br> "message": "수강신청 목록 조회",<br> "data": [<br> &nbsp;&nbsp;{ "id": 1, "lectureId": 2, "lectureName": "논리학 개론", ... },<br> &nbsp;&nbsp;{ "id": 3, "lectureId": 3, "lectureName": "화학 실험", ... },<br> &nbsp;&nbsp;{ "id": 4, "lectureId": 1, "lectureName": "웹 프로그래밍", ... }<br> ],<br> "timestamp": "2025-07-12T14:44:26.4098048"<br>} |
| GET | /lectures/enroll/{lectureId}/count | 특정 강의 수강신청 인원 수 조회 |  | {<br> "success": true,<br> "message": "해당 강의 수강신청 인원 수 조회",<br> "data": {<br> &nbsp;&nbsp;"lectureId": 2,<br> &nbsp;&nbsp;"maxStudent": 25,<br> &nbsp;&nbsp;"student": 1<br> },<br> "timestamp": "2025-07-12T15:37:47.0010355"<br>} |
| DELETE | /lectures/enroll/{lectureMemberId} | 수강신청 취소 |  | {<br> "success": true,<br> "message": "수강신청 취소",<br> "data": null,<br> "timestamp": "2025-07-09T11:17:15.0377381"<br>} |


[✏️API 명세서 상세 (notion으로 이동)](https://www.notion.so/teamsparta/API-22a2dc3ef51480a2b5dcd9bee3b33d1e)
</details>
<br/><br/>

## 7. 🚀 트러블 슈팅 / 기술적 의사결정
[✏️5분 기록보드(notion으로 이동)](https://www.notion.so/teamsparta/5-2312dc3ef514804d903dc40926c14d53?source=copy_link)
<br/><br/>

## 8. 🚩 프로젝트 일정[Jira]
25.07.07 ~ 25.07.15
[✏️프로젝트 일정 자세히 보기(Jira로 이동)](https://xodndd515.atlassian.net/jira/software/projects/CPG/boards/1/timeline)   

<br/><br/>
## 9. 📌 기여자

| <a href="https://github.com/taeung515"><img src="https://github.com/taeung515.png" width="80" style="border-radius: 50%" /></a> | <a href="https://github.com/youngee2"><img src="https://github.com/youngee2.png" width="80" style="border-radius: 50%" /></a> | <a href="https://github.com/Ddiy0ng"><img src="https://github.com/Ddiy0ng.png" width="80" style="border-radius: 50%" /></a> | <a href="https://github.com/hojunyun-dev"><img src="https://github.com/hojunyun-dev.png" width="80" style="border-radius: 50%" /></a> | <a href="https://github.com/Gyum1"><img src="https://github.com/Gyum1.png" width="80" style="border-radius: 50%" /></a> | <a href="https://github.com/YoungJae1118"><img src="https://github.com/YoungJae1118.png" width="80" style="border-radius: 50%" /></a> |
| :-----------------------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------------------: |
|                                                               최태웅                                                               |                                                            장아영                                                            |                                                             박소희|                                                              윤호준                                                              |                                                            이태겸                                                            |                                                            이영재                                                            |
