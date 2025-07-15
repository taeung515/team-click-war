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
- 추가 예정
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
